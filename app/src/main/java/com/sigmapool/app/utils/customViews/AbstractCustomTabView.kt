package com.sigmapool.app.utils.customViews

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData

abstract class AbstractCustomTabView {

    abstract val leftTabText: String
    abstract val rightTabText: String

    val leftActivatedLiveData = MutableLiveData(true)
    val rightActivatedLiveData = MutableLiveData(false)

    @CallSuper
    open fun leftClickAction() {
        if (leftActivatedLiveData.value == false) {
            leftActivatedLiveData.postValue(true)
            rightActivatedLiveData.postValue(false)
        }
    }

    @CallSuper
    open fun rightClickAction() {
        if (rightActivatedLiveData.value == false) {
            leftActivatedLiveData.postValue(false)
            rightActivatedLiveData.postValue(true)
        }
    }
}