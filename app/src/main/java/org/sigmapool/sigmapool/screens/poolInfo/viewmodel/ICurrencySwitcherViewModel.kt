package org.sigmapool.sigmapool.screens.poolInfo.viewmodel

import androidx.databinding.ObservableBoolean
import org.sigmapool.sigmapool.screens.calculator.viewModel.CoinTabVM

interface ICurrencySwitcherViewModel {
    val btcSelected: ObservableBoolean
    val coinTabVM: CoinTabVM

    fun btcSelect()
    fun ltcSelect()

}