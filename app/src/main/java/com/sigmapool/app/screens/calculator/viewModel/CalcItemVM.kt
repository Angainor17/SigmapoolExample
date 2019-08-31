package com.sigmapool.app.screens.calculator.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalcItemVM(
    val coinLabel: String
) : ViewModel() {

    val price = MutableLiveData("~ \$ 368.48")//FIXME

    val currentPrice = MutableLiveData<CharSequence>("\$ 11 224.42")
    val difficulty  = MutableLiveData<CharSequence>("7.93 T")
    val blockReward = MutableLiveData<CharSequence>("12.543 BTC")

    val clickAction = View.OnClickListener {
        Log.d("", "")
    }
}