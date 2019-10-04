package org.sigmapool.sigmapool.provider.coin

import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.home.coin.CoinVm

class CoinProvider : ICoinProvider {

    private val resProvider by App.kodein.instance<IResProvider>()

    private val ltcCoin = CoinVm(resProvider.getString(R.string.ltc), R.mipmap.ic_ltc)
    private val btcCoin = CoinVm(resProvider.getString(R.string.btc), R.mipmap.ic_btc)

    override val coins = arrayListOf(btcCoin, ltcCoin)

    private var selectedCoin = btcCoin

    private var listener: ((String) -> Unit)? = null

    override fun getLabel(): String = selectedCoin.text

    override fun addOnChangeListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    override fun onCoinSelected(coin: CoinVm) {
        if (selectedCoin != coin) {
            selectedCoin = coin
            listener?.invoke(coin.text)
        }
    }
}