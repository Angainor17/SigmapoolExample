package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.provider.coin.ICoinProvider

class DashboardChartInfoVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    val coin = MutableLiveData<String>(coinProvider.getLabel().toUpperCase())

    val avgHashrateValue = MutableLiveData<String>("156.25")
    val avgHashratePostfix = MutableLiveData<String>("PH/s")

    val earnedLastValue = MutableLiveData<String>("0.04357")

    val balanceValue = MutableLiveData<String>("0.04357")

    val onlineWorker = MutableLiveData<String>("135")
    val allWorker = MutableLiveData<String>("145")

}