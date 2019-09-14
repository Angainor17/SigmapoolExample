package com.sigmapool.app.utils.customViews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomSwitchVm(
    val leftText: String = "",
    val rightText: String = ""
) : ViewModel() {

    val leftActivated = MutableLiveData(false)
    val rightActivated = MutableLiveData(true)

    fun rightClick() {
        if (!rightActivated.value!!) {
            leftActivated.postValue(false)
            rightActivated.postValue(true)
        }
    }

    fun leftClick() {
        if (!leftActivated.value!!) {
            rightActivated.postValue(false)
            leftActivated.postValue(true)
        }
    }
}