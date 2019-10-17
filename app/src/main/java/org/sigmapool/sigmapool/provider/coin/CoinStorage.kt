package org.sigmapool.sigmapool.provider.coin

import org.sigmapool.sigmapool.screens.home.coin.CoinVm

object CoinStorage {

    private val coins = ArrayList<CoinVm>()

    fun getCoins() = coins

    fun saveCoins(newCoins: ArrayList<CoinVm>?) {
        if (!newCoins.isNullOrEmpty()) {
            coins.clear()
            coins.addAll(newCoins)
        }
    }
}
