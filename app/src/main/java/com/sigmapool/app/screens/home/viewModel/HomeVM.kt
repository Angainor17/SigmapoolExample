package com.sigmapool.app.screens.home.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.provider.lang.ILanguageProvider
import com.sigmapool.app.screens.home.coin.CoinsVM
import com.sigmapool.app.screens.login.LoginFragment
import com.sigmapool.common.managers.IBlogManager
import com.sigmapool.common.models.BlogDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import ss.com.bannerslider.event.OnSlideClickListener

private const val REGISTER_URL = "https://btc.sigmapool.com/signup"

private const val BLOG_INIT_PAGE = 1
private const val BLOG_PER_PAGE = 20

class HomeVM : ViewModel(), OnSlideClickListener {

    private val blogManager by App.kodein.instance<IBlogManager>()
    private val langProvider by App.kodein.instance<ILanguageProvider>()

    val urlLiveData = MutableLiveData<String>()
    val fragmentLiveData = MutableLiveData<Class<out Fragment>>()
    val blogImages = MutableLiveData<ArrayList<BlogDto>>()

    val homeMenuVM = HomeMenuVM(fragmentLiveData)
    val coinsVM = CoinsVM()

    init {
        initBlogBanner()
    }

    private fun initBlogBanner() {
        GlobalScope.launch(Dispatchers.IO) {
            val coin = blogManager.getBlogs(BLOG_INIT_PAGE, BLOG_PER_PAGE, langProvider.getLangShortName())
            if (coin.success) {
                blogImages.postValue(coin.data ?: ArrayList())
            } else {
                blogImages.postValue(arrayListOf(BlogDto("", "noIcon")))
            }
        }
    }

    override fun onSlideClick(position: Int) {
        if (position >= blogImages.value?.size ?: 0) return

        val item = blogImages.value?.get(position)
        urlLiveData.postValue(item?.url)
    }

    fun register() {
        urlLiveData.postValue(REGISTER_URL)
    }

    fun login() {
        fragmentLiveData.postValue(LoginFragment::class.java)
    }
}