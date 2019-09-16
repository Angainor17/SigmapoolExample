package com.sigmapool.app.screens.dashboard.viewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.common.models.CurrencyDto
import com.sigmapool.common.models.NetworkDto
import com.sigmapool.common.utils.*
import org.kodein.di.generic.instance

const val SECONDS_IN_MINUTE = 60

class DashboardNetworkStatusVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    private val currencyProvider by kodein.instance<ICurrencyProvider>()
    private val res by kodein.instance<IResProvider>()

    val hashrate = MutableLiveData(0.0f.trimZeroEnd())
    val hashratePostfix = MutableLiveData(res.getString(R.string.hashrate_per_second))

    val difficulty = MutableLiveData(0.format(INT_PATTERN))

    val blockTime = MutableLiveData("")
    val blockTimePostfix = MutableLiveData(res.getString(R.string.second))

    val pricePrefix = MutableLiveData(currencyProvider.getSymbol())
    val price = MutableLiveData(0f.format(INT_PATTERN))

    val blockReward = MutableLiveData(0f.trimZeroEnd())
    val blockRewardPostfix = MutableLiveData(coinProvider.getLabel())

    fun initNetworkDto(network: NetworkDto) {
        blockReward.postValue(network.blockReward.trimZeroEnd())
        blockRewardPostfix.postValue(coinProvider.getLabel())

        if (network.blockTime < SECONDS_IN_MINUTE) {
            blockTime.postValue("" + network.blockTime)
            blockTimePostfix.postValue(res.getString(R.string.second))
        } else {
            blockTime.postValue("" + network.blockTime / SECONDS_IN_MINUTE)
            blockTimePostfix.postValue(res.getString(R.string.minute))
        }

        val hashrateValue = formatLongValue(network.networkHashrate.toLong(), FLOAT_PATTERN)

        hashrate.postValue(hashrateValue.beforeLastChar())
        hashratePostfix.postValue(
            if (hashrateValue.lastChar().isDigitsOnly()) "" else hashrateValue.lastChar() +
                    res.getString(R.string.hashrate_per_second)
        )
        difficulty.postValue(network.networkDifficulty.toInt().format(INT_PATTERN))

    }

    fun initCurrency(currency: CurrencyDto) {
        price.postValue(
            currencyProvider.fromUsdToCurrency(currency.usd).toInt().format(INT_PATTERN)
        )
    }
}