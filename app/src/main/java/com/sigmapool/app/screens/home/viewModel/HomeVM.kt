package com.sigmapool.app.screens.home.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.lang.ILocaleProvider
import com.sigmapool.app.screens.home.coin.CoinsVM
import com.sigmapool.app.screens.login.LoginFragment
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.screens.news.params.NewsListParams
import com.sigmapool.app.screens.news.vm.NewsListVM
import com.sigmapool.app.utils.interfaces.IUpdateScreenVm
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.managers.IBlogManager
import com.sigmapool.common.models.AuthDto
import com.sigmapool.common.models.BlogDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import ss.com.bannerslider.event.OnSlideClickListener

private const val REGISTER_URL = "https://btc.sigmapool.com/signup"

private const val BLOG_INIT_PAGE = 1
private const val BLOG_PER_PAGE = 20
private const val NEWS_PER_PAGE = 3

class HomeVM : ViewModel(), OnSlideClickListener, IUpdateScreenVm {

    private val blogManager by kodein.instance<IBlogManager>()
    private val langProvider by kodein.instance<ILocaleProvider>()
    private val storage by kodein.instance<JsonDataStorage>()

    val isLogin = MutableLiveData<Boolean>()
    val urlLiveData = MutableLiveData<String>()
    val fragmentLiveData = MutableLiveData<Class<out Fragment>>()
    val blogImages = MutableLiveData<ArrayList<BlogDto>>()

    val homeMenuVM = HomeMenuVM(fragmentLiveData)
    val coinsVM = CoinsVM()
    val minersVM = HomeMinerVM()
    val newsVM = NewsListVM(NewsListParams(NEWS_PER_PAGE))

    init {
        initBlogBanner()
    }

    override fun update() {
        refreshAuth()
    }

    override fun onSlideClick(position: Int) {
        if (position >= blogImages.value?.size ?: 0 || position < 0) return

        val item = blogImages.value?.get(position)
        urlLiveData.postValue(item?.url)
    }

    fun register() {
        urlLiveData.postValue(REGISTER_URL)
    }

    fun login() {
        fragmentLiveData.postValue(LoginFragment::class.java)
    }

    private fun initBlogBanner() {
        GlobalScope.launch(Dispatchers.IO) {
            val coin = blogManager.getBlogs(BLOG_INIT_PAGE, BLOG_PER_PAGE, langProvider.getLocale().locale)
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
