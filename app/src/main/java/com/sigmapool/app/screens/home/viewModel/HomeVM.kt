package com.sigmapool.app.screens.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val REGISTER_URL = "https://btc.sigmapool.com/signup"

class HomeVM : ViewModel() {

    val urlLiveData = MutableLiveData<String>()

    fun register() {
        urlLiveData.postValue(REGISTER_URL)
    }

    fun login() {
        //TODO implement
    }
}