package org.sigmapool.sigmapool.utils.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ErrorHandleVm : ViewModel() {

    val errorLiveData = MutableLiveData<String>()

}