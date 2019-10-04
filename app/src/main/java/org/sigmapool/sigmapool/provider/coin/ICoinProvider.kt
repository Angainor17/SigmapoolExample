package org.sigmapool.sigmapool.provider.coin

import org.sigmapool.sigmapool.screens.home.coin.CoinVm

interface ICoinProvider {

    val coins: ArrayList<CoinVm>

    fun getLabel(): String

    fun addOnChangeListener(listener: (String) -> Unit)

    fun onCoinSelected(coin: CoinVm)

}