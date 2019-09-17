package com.sigmapool.app.screens.poolInfo.viewmodel

import androidx.databinding.ObservableBoolean
import com.sigmapool.app.screens.calculator.viewModel.CoinTabVM

interface ICurrencySwitcherViewModel {
    val btcSelected: ObservableBoolean
    val coinTabVM: CoinTabVM

    fun btcSelect()
    fun ltcSelect()

}