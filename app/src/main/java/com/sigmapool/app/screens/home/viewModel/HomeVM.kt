package com.sigmapool.app.screens.home.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.login.LoginFragment

private const val REGISTER_URL = "https://btc.sigmapool.com/signup"

class HomeVM : ViewModel() {

    val urlLiveData = MutableLiveData<String>()
    val fragmentLiveData = MutableLiveData<Class<out Fragment>>()

    fun register() {
        urlLiveData.postValue(REGISTER_URL)
    }

    fun login() {
        fragmentLiveData.postValue(LoginFragment::class.java)
    }
}