package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.common.utils.INT_PATTERN
import com.sigmapool.common.utils.format
import org.kodein.di.generic.instance

class DashboardNetworkStatusVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    private val currencyProvider by kodein.instance<ICurrencyProvider>()

    val hashrate = MutableLiveData("68544.54")//FIXME
    val hashratePostfix = MutableLiveData("PH/s")//FIXME

    val difficulty = MutableLiveData("7 563 659 124 765")//FIXME

    val blockTime = MutableLiveData("10")//FIXME
    val blockTimePostfix = MutableLiveData("min")//FIXME

    val pricePrefix = MutableLiveData(currencyProvider.getSymbol())
    val price = MutableLiveData(currencyProvider.fromUsdToCurrency(11546f).toInt().format(//FIXME
        INT_PATTERN))

    val blockReward = MutableLiveData("12.543")//FIXME
    val blockRewardPostfix = MutableLiveData(coinProvider.getLabel())

}