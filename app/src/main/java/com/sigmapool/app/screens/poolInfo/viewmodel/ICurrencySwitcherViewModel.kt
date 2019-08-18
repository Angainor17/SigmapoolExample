package com.sigmapool.app.screens.poolInfo.viewmodel

import androidx.databinding.ObservableBoolean

interface ICurrencySwitcherViewModel {
    val btcSelected: ObservableBoolean
    fun btcSelect()
    fun ltcSelect()
    //fun setCurrencySelected(isSelected: Boolean)
}