package com.sigmapool.app.screens.home.coin

import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.home.viewModel.CoinItemVM

class CoinsVM : ViewModel() {

    val btcCoinVM = CoinItemVM()
    val ltcCoinVM = CoinItemVM()

    fun getCoins() = arrayListOf(btcCoinVM, ltcCoinVM)

}