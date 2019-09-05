package com.sigmapool.app.provider.coin

class CoinProvider : ICoinProvider {

    var coin = "btc"

    override fun getLabel(): String {
        return coin
    }

    override fun addChangeListener(listener: (String) -> Unit) {

    }
}