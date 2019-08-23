package com.sigmapool.app.screens.home.coin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.ViewState
import com.sigmapool.app.utils.plus
import com.sigmapool.app.utils.spannableString
import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.NetworkDto
import com.sigmapool.common.models.PaymentDto
import com.sigmapool.common.models.ProfitDailyDto
import com.sigmapool.common.utils.FLOAT_PATTERN
import com.sigmapool.common.utils.format
import org.kodein.di.generic.instance

class CoinItemVM : ViewModel() {

    private val resProvider by App.kodein.instance<IResProvider>()
    private val currencyProvider by App.kodein.instance<ICurrencyProvider>()

    val viewState = MutableLiveData(ViewState.LOADING)

    val coinLabel = MutableLiveData<String>()
    val coinPrice = MutableLiveData<String>()
    val coinPriceChange = MutableLiveData<String>()
    val isCoinPriceUp = MutableLiveData<Boolean>()

    val poolHashrate = MutableLiveData<CharSequence>()
    val workerCount = MutableLiveData<String>()
    val minPayment = MutableLiveData<CharSequence>()
    val paymentTime = MutableLiveData<String>()
    val payoutScheme = MutableLiveData<String>()
    val profit = MutableLiveData<CharSequence>()

    fun formatPoolHasrate(value: Float): CharSequence = formatValue(
        "$value ",
        coinLabel.value ?: ""
    )

    fun formatMinPayment(value: Float): CharSequence = formatValue(
        value.format(FLOAT_PATTERN) + " ",
        resProvider.getString(R.string.pool_hashrate_per_second)
    )

    fun payoutScheme(): String {
        return ""
    }

    fun formatProfit(value: Float): CharSequence = formatValue(
        "$value ",
        coinLabel.value ?: ""
    )

    private fun formatValue(value: String, postfix: String): CharSequence =
        spannableString(value) + spannableString(postfix, color = resProvider.getColor(R.color.titleGray))

    fun initVMs(coinDto: CoinDto, networkDto: NetworkDto, paymentDto: PaymentDto, profitDailyDto: ProfitDailyDto) {
        //TODO
    }
}

