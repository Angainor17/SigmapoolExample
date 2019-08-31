package com.sigmapool.app.screens.calculator.viewModel

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalcTabItemVM(
    val text: String,
    @DrawableRes val icon: Int,
    val activatedLiveData: MutableLiveData<Boolean>
) : ViewModel() {

    fun value() = activatedLiveData.value

    fun postValue(isClicked: Boolean) {
        activatedLiveData.postValue(isClicked)
    }
}