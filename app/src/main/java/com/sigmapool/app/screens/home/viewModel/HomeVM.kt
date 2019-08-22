package com.sigmapool.app.screens.home.viewModel

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.screens.login.LoginFragment
import com.sigmapool.common.managers.IBlogManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

private const val REGISTER_URL = "https://btc.sigmapool.com/signup"

class HomeVM : ViewModel() {

    private val blogManager by App.kodein.instance<IBlogManager>()

    val urlLiveData = MutableLiveData<String>()
    val fragmentLiveData = MutableLiveData<Class<out Fragment>>()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            val coin = blogManager.getBlogs(1,20,"en")
            if (coin.success) {
                val result = coin.data
                Log.d("","")
            }
        }
    }

    fun register() {
        urlLiveData.postValue(REGISTER_URL)
    }

    fun login() {
        fragmentLiveData.postValue(LoginFragment::class.java)
    }
}