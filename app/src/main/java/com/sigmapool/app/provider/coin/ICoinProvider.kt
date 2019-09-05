package com.sigmapool.app.provider.coin

import com.sigmapool.app.screens.home.coin.CoinVm

interface ICoinProvider {

    val coins: ArrayList<CoinVm>

    fun getLabel(): String

    fun addOnChangeListener(listener: (String) -> Unit)

    fun onCoinSelected(coin: CoinVm)

}