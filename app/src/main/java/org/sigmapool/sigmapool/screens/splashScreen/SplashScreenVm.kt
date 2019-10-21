package org.sigmapool.sigmapool.screens.splashScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.coin.ICoinProvider

class SplashScreenVm(val view: ISplashScreenVIew) : ViewModel() {

    val coinProvider by kodein.instance<ICoinProvider>()

    val loadingVisibility = MutableLiveData(true)
    val errorVisibility = MutableLiveData(false)

    init {
        getInitData()
    }

    fun onRefresh() {
        loadingVisibility.postValue(true)
        errorVisibility.postValue(false)
    }

    private fun getInitData() {
        GlobalScope.launch(Dispatchers.IO) {
            val coinDeferred = async { coinProvider.init()}

            coinDeferred.await()
        }
    }
}