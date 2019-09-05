package com.sigmapool.app.utils.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ErrorHandleVm : ViewModel() {

    val errorLiveData = MutableLiveData<String>()

}