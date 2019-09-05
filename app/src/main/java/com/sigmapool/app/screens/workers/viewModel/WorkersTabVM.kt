package com.sigmapool.app.screens.workers.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkersTabVM : ViewModel() {

    val onlineHashrate = MutableLiveData("0 TH/s")

    val onlineCount = MutableLiveData("0")
    val offlineCount = MutableLiveData("0")
    val totalCount = MutableLiveData("0")

    fun onlineTabSelected() {
        Log.d("", "")
        //TODO
    }

    fun offlineTabSelected() {
        Log.d("", "")
        //TODO
    }

    fun allTabSelected() {
        Log.d("", "")
        //TODO
    }
}