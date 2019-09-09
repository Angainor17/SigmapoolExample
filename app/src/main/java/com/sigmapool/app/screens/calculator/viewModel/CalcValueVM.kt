package com.sigmapool.app.screens.calculator.viewModel

import androidx.lifecycle.MutableLiveData

class CalcValueVM(label: String, postfix: String, value: String, price: String = "") {

    val label = MutableLiveData(label)
    val postfix = MutableLiveData(postfix)
    val value = MutableLiveData(value)
    val price = MutableLiveData(price)

}
