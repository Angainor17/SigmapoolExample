package com.sigmapool.app.screens.calculator.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalcItemVM(
    val coinLabel: String
) : ViewModel() {

    val price = MutableLiveData("~ \$ 368.48")//FIXME

    val clickAction = View.OnClickListener {
        Log.d("", "")
    }
}