package org.sigmapool.sigmapool.screens.home.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IBlogManager
import org.sigmapool.common.models.AuthDto
import org.sigmapool.common.models.BlogDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.lang.ILocaleProvider
import org.sigmapool.sigmapool.screens.home.coin.CoinsVM
import org.sigmapool.sigmapool.screens.login.data.AUTH_KEY
import org.sigmapool.sigmapool.utils.interfaces.IBrowserVm
import org.sigmapool.sigmapool.utils.interfaces.IUpdateScreenVm
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage
import ss.com.bannerslider.event.OnSlideClickListener

private const val REGISTER_URL = "https://btc.sigmapool.com/signup"

private const val BLOG_INIT_PAGE = 1
private const val BLOG_PER_PAGE = 20
private const val DISPLAYED_NEWS = 3
private const val NEWS_PAGE_SIZE = 1000

class HomeVM : ViewModel(), OnSlideClickListener, IUpdateScreenVm, IBrowserVm {

    private val blogManager by kodein.instance<IBlogManager>()
    private val langProvider by kodein.instance<ILocaleProvider>()
    private val storage by kodein.instance<JsonDataStorage>()

    val isLogin = MutableLiveData<Boolean>()

    val fragmentLiveData = MutableLiveData<Class<out Fragment>>()
    val blogImages = MutableLiveData<ArrayList<BlogDto>>()
    val homeMenuVM = HomeMenuVM(fragmentLiveData)

    val coinsVM = CoinsVM()
    val minersVM = HomeMinerVM()
    val newsVM = NewsListVM(NewsListParams(NEWS_PAGE_SIZE, DISPLAYED_NEWS))

    override val urlLiveData = newsVM.urlLiveData

    init {
        initBlogBanner()
    }

    override fun update() {
        refreshAuth()
    }

    override fun onSlideClick(position: Int) {
        val updatedPosition = if (position < 0) 0 else position
        if (updatedPosition >= blogImages.value?.size ?: 0 || updatedPosition < 0) return

        val item = blogImages.value?.get(updatedPosition)
        urlLiveData.postValue(item?.url)
    }

    fun register() {
        urlLiveData.postValue(REGISTER_URL)
    }

    fun login() {
//        fragmentLiveData.postValue(LoginFragment::class.java)
    }

    private fun initBlogBanner() {
        GlobalScope.launch(Dispatchers.IO) {
            val coin =
                blogManager.getBlogs(BLOG_INIT_PAGE, BLOG_PER_PAGE, langProvider.getLocale().locale)
            if (coin.success) {
                blogImages.postValue(coin.data ?: ArrayList())
            } else {
                blogImages.postValue(arrayListOf(BlogDto("", "noIcon")))
            }
        }
    }

    private fun refreshAuth() {
        val authDto = Gson().fromJson(storage.getJson(AUTH_KEY), AuthDto::class.java)
        isLogin.postValue(authDto != null)
    }
}
