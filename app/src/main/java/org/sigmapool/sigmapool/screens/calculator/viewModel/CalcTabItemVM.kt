package org.sigmapool.sigmapool.screens.calculator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalcTabItemVM(
    val text: String,
    val iconUrl: LiveData<String>,
    val activatedLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
) : ViewModel() {

    fun value() = activatedLiveData.value

    fun postValue(isClicked: Boolean) {
        activatedLiveData.postValue(isClicked)
    }
}