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
import com.sigmapool.common.models.*
import com.sigmapool.common.utils.FLOAT_PATTERN
import com.sigmapool.common.utils.INT_PATTERN
import com.sigmapool.common.utils.format
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*

class CoinItemVM(val coinLabel: String) : ViewModel() {

    private val resProvider by App.kodein.instance<IResProvider>()
    private val currencyProvider by App.kodein.instance<ICurrencyProvider>()

    val viewState = MutableLiveData(ViewState.LOADING)

    val coinPrice = MutableLiveData<CharSequence>()
    val coinPriceChange = MutableLiveData<String>()
    val isCoinPriceUp = MutableLiveData<Boolean>()

    val poolHashrate = MutableLiveData<CharSequence>()
    val workerCount = MutableLiveData<String>()
    val minPayment = MutableLiveData<CharSequence>()
    val paymentTime = MutableLiveData<String>()
    val payoutScheme = MutableLiveData<String>()
    val profit = MutableLiveData<CharSequence>()

    fun formatProfit(value: Float): CharSequence = formatValue("$value ", coinLabel.toUpperCase())

    fun initVMs(coinDto: CoinDto, networkDto: NetworkDto, paymentDto: PaymentDto, profitDailyDto: ProfitDailyDto) {
        coinPrice.postValue(
            spannableString(currencyProvider.getSymbol(), color = resProvider.getColor(R.color.titleGray)) +
                    spannableString(" " + coinDto.price.toInt().format(INT_PATTERN))
        )
        coinPriceChange.postValue("9.54 %")//FIXME
        isCoinPriceUp.postValue(false)//FIXME

        poolHashrate.postValue(formatPoolHashrate(156.25f))//FIXME
        workerCount.postValue(coinDto.poolWorkers.format(INT_PATTERN))
        minPayment.postValue(formatMinPayment(paymentDto.min))
        paymentTime.postValue(formatPaymentTime(paymentDto.time))
        payoutScheme.postValue(coinDto.payoutScheme.joinToString("/").toUpperCase())
        profit.postValue(formatValue("" + profitDailyDto.profit, " " + coinLabel.toUpperCase()))
    }

    private fun formatMinPayment(value: Float): CharSequence = formatValue(
        value.format(FLOAT_PATTERN) + " ", coinLabel.toUpperCase()
    )

    private fun formatPaymentTime(timeInterval: TimeIntervalDto): String {
        return formatTime(timeInterval.from) + "-" + formatTime(timeInterval.to)
    }

    private fun formatPoolHashrate(value: Float): CharSequence =
        formatValue("$value ", resProvider.getString(R.string.pool_hashrate_per_second))

    private fun formatValue(value: String, postfix: String): CharSequence =
        spannableString(value) + spannableString(postfix, color = resProvider.getColor(R.color.titleGray))
}

fun formatTime(date: Date): String = SimpleDateFormat("HH:mm").format(date)

