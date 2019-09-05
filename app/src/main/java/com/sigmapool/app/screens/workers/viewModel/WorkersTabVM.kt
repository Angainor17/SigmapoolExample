package com.sigmapool.app.screens.workers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkersTabVM(private val screenPositionLiveData: MutableLiveData<Int>) : ViewModel() {

    val onlineHashrate = MutableLiveData("0 TH/s")

    val onlineCount = MutableLiveData("0")
    val offlineCount = MutableLiveData("0")
    val totalCount = MutableLiveData("0")

    fun onlineTabSelected() {
        screenPositionLiveData.postValue(0)
    }

    fun offlineTabSelected() {
        screenPositionLiveData.postValue(1)
    }

    fun allTabSelected() {
        screenPositionLiveData.postValue(2)
    }
}