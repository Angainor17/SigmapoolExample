package com.sigmapool.app.screens.home.coin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.ViewState
import com.sigmapool.app.utils.formatLongValue
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

    val networkHashrate = MutableLiveData<CharSequence>()
    val networkDifficulty = MutableLiveData<CharSequence>()
    val block = MutableLiveData<String>()
    val nextDifficultyAt = MutableLiveData<String>()
    val nextDifficulty = MutableLiveData<CharSequence>()

    val nextDifficultyChange = MutableLiveData<String>()
    val isNextDifficultyChangeUp = MutableLiveData<Boolean>()

    fun initVMs(coinDto: CoinDto, networkDto: NetworkDto, paymentDto: PaymentDto, profitDailyDto: ProfitDailyDto) {
        coinPrice.postValue(
            spannableString(currencyProvider.getSymbol(), color = resProvider.getColor(R.color.titleGray)) +
                    spannableString(" " + coinDto.price.toInt().format(INT_PATTERN))
        )
        coinPriceChange.postValue("9.54 %")//FIXME
        isCoinPriceUp.postValue(coinDto.previousPrice < coinDto.price)

        poolHashrate.postValue(formatHashrate(coinDto.poolHashrate))
        workerCount.postValue(coinDto.poolWorkers.format(INT_PATTERN))
        minPayment.postValue(formatMinPayment(paymentDto.min))
        paymentTime.postValue(formatPaymentTime(paymentDto.time))
        payoutScheme.postValue(coinDto.payoutScheme.joinToString("/").toUpperCase())
        profit.postValue(
            formatValue(
                String.format("%f", profitDailyDto.profit),
                " " + coinLabel.toUpperCase()
            )
        )

        networkHashrate.postValue(formatHashrate(networkDto.networkHashrate.toLong()))
        networkDifficulty.postValue(formatNetworkDifficulty(networkDto))
        block.postValue(networkDto.blockHeight.format(INT_PATTERN))
        nextDifficultyAt.postValue(formatDate(networkDto.nextDifficultyAt))
        nextDifficulty.postValue(formatValue("7.93", " T"))//FIXME

        nextDifficultyChange.postValue("9.54 %")//FIXME
        isNextDifficultyChangeUp.postValue(false)//FIXME
    }

    private fun formatNetworkDifficulty(networkDto: NetworkDto): CharSequence {
        val result = formatLongValue(networkDto.networkDifficulty, FLOAT_PATTERN)

        return formatValue(result.beforeLastChar(), " " + result.lastChar())
    }

    private fun formatMinPayment(value: Float): CharSequence = formatValue(
        value.format(FLOAT_PATTERN) + " ", coinLabel.toUpperCase()
    )

    private fun formatPaymentTime(timeInterval: TimeIntervalDto): String {
        return formatTime(timeInterval.from) + "-" + formatTime(timeInterval.to)
    }

    private fun formatHashrate(value: Long): CharSequence {
        val result = formatLongValue(value, FLOAT_PATTERN)

        return formatValue(
            result.beforeLastChar() + " ",
            result.lastChar() + resProvider.getString(R.string.hashrate_per_second)
        )
    }

    private fun formatValue(value: String, postfix: String): CharSequence =
        spannableString(value) + spannableString(postfix, color = resProvider.getColor(R.color.titleGray))
}

fun String.lastChar() = substring(length - 1)
fun String.beforeLastChar() = substring(0, length - 1)

fun formatTime(date: Date): String = SimpleDateFormat("HH:mm").format(date)
fun formatDate(date: Date): String = SimpleDateFormat("dd.MM.yyyy").format(date)

