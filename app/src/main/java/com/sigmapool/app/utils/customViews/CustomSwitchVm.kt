package com.sigmapool.app.utils.customViews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomSwitchVm(
    val leftText: String = "",
    val rightText: String = ""
) : ViewModel() {

    var clickListener: OnSwitchSelected? = null

    val leftActivated = MutableLiveData(false)
    val rightActivated = MutableLiveData(true)

    fun rightClick() {
        if (!rightActivated.value!!) {
            leftActivated.postValue(false)
            rightActivated.postValue(true)

            clickListener?.onSelected(false)
        }
    }

    fun leftClick() {
        if (!leftActivated.value!!) {
            rightActivated.postValue(false)
            leftActivated.postValue(true)

            clickListener?.onSelected(true)
        }
    }
}

interface OnSwitchSelected {
    fun onSelected(leftSelected: Boolean)
}