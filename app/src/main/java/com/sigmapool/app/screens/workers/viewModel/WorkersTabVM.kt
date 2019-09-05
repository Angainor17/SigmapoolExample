package com.sigmapool.app.screens.workers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkersTabVM : ViewModel() {

    val onlineHasrate = MutableLiveData("")

    val onlineCount = MutableLiveData(0)
    val offlineCount = MutableLiveData(0)
    val totalCount = MutableLiveData(0)


}