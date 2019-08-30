package com.sigmapool.app.utils.customViews

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData

abstract class AbstractCustomTabView(private val tabPositionLiveData: MutableLiveData<Int>) {

    abstract val leftTabText: String
    abstract val rightTabText: String

    val leftActivatedLiveData = MutableLiveData(true)
    val rightActivatedLiveData = MutableLiveData(false)

    @CallSuper
    open fun leftClickAction() {
        if (leftActivatedLiveData.value == false) {
            leftActivatedLiveData.postValue(true)
            rightActivatedLiveData.postValue(false)
            tabPositionLiveData.postValue(0)
        }
    }

    @CallSuper
    open fun rightClickAction() {
        if (rightActivatedLiveData.value == false) {
            leftActivatedLiveData.postValue(false)
            rightActivatedLiveData.postValue(true)
            tabPositionLiveData.postValue(1)
        }
    }
}