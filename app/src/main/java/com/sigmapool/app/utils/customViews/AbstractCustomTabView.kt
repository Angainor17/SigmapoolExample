package com.sigmapool.app.utils.customViews

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.screens.calculator.viewModel.CalcTabItemVM

abstract class AbstractCustomTabView(private val tabPositionLiveData: MutableLiveData<Int>) {

    abstract val leftTab: CalcTabItemVM
    abstract val rightTab: CalcTabItemVM

    @CallSuper
    open fun leftClickAction() {
        if (leftTab.value() == false) {
            leftTab.postValue(true)
            rightTab.postValue(false)
            tabPositionLiveData.postValue(0)
        }
    }

    @CallSuper
    open fun rightClickAction() {
        if (rightTab.value() == false) {
            leftTab.postValue(false)
            rightTab.postValue(true)
            tabPositionLiveData.postValue(1)
        }
    }
}