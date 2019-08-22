package com.sigmapool.app.screens.home.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
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
private const val BLOG_LANG = "en"

class HomeVM : ViewModel(), OnSlideClickListener {

    private val blogManager by App.kodein.instance<IBlogManager>()

    val urlLiveData = MutableLiveData<String>()
    val fragmentLiveData = MutableLiveData<Class<out Fragment>>()
    val blogImages = MutableLiveData<ArrayList<BlogDto>>()

    init {
        initBlogBanner()
    }

    private fun initBlogBanner() {
        GlobalScope.launch(Dispatchers.IO) {
            val coin = blogManager.getBlogs(BLOG_INIT_PAGE, BLOG_PER_PAGE, BLOG_LANG)
            if (coin.success) {
                coin.data?.forEach {
                    it.imageUrl =
                        "https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg"
                }//FIXME remove
                blogImages.postValue(coin.data ?: ArrayList())
            } else {
                blogImages.postValue(arrayListOf(BlogDto("", "noIcon")))
            }
        }
    }

    override fun onSlideClick(position: Int) {
        if (position <= blogImages.value?.size ?: 0) return

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