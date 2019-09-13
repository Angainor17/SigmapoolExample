package com.sigmapool.app.screens.home.coin

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.interfaces.StateVM
import com.sigmapool.app.utils.interfaces.ViewState
import com.sigmapool.common.models.*
import com.sigmapool.common.utils.*
import org.kodein.di.generic.instance

class CoinItemVM(val coinLabel: String, @DrawableRes val iconRes: Int) : ViewModel(),
    StateVM {

    private val resProvider by kodein.instance<IResProvider>()
    private val currencyProvider by kodein.instance<ICurrencyProvider>()

    override val viewState = MutableLiveData(ViewState.LOADING)

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

    fun initVMs(
        coinDto: CoinDto,
        networkDto: NetworkDto,
        paymentDto: PaymentDto,
        profitDailyDto: ProfitDailyDto
    ) {
        coinPrice.postValue(
            spannableString(
                currencyProvider.getSymbol(),
                color = resProvider.getColor(R.color.titleGray)
            ) +
                    spannableString(
                        " " + currencyProvider.fromUsdToCurrency(coinDto.price).toInt().format(
                            INT_PATTERN
                        )
                    )
        )
        coinPriceChange.postValue(
            formatPercent(
                coinDto.previousPrice,
                coinDto.price
            )
        )
        isCoinPriceUp.postValue(coinDto.previousPrice < coinDto.price)

        poolHashrate.postValue(formatHashrate(coinDto.poolHashrate))
        workerCount.postValue(coinDto.poolWorkers.format(INT_PATTERN))
        minPayment.postValue(formatMinPayment(paymentDto.min))
        paymentTime.postValue(formatPaymentTime(paymentDto.time))
        payoutScheme.postValue(coinDto.payoutScheme.joinToString("/").toUpperCase())
        profit.postValue(
            formatValueWithPostfix(
                String.format("%f", profitDailyDto.profit),
                " " + coinLabel.toUpperCase(),
                resProvider.getColor(R.color.titleGray)
            )
        )
        networkHashrate.postValue(formatHashrate(networkDto.networkHashrate.toLong()))
        networkDifficulty.postValue(formatDifficulty(networkDto.networkDifficulty.toLong()))
        block.postValue(networkDto.blockHeight.format(INT_PATTERN))
        nextDifficultyAt.postValue(networkDto.nextDifficultyAt.formatDate())
        nextDifficulty.postValue(formatDifficulty(networkDto.nextDifficulty.toLong()))
        nextDifficultyChange.postValue(
            formatPercent(
                networkDto.networkDifficulty,
                networkDto.nextDifficulty
            )
        )
        isNextDifficultyChangeUp.postValue(networkDto.nextDifficulty > networkDto.networkDifficulty)
    }

    private fun formatDifficulty(value: Long): CharSequence {
        val result = formatLongValue(value, FLOAT_PATTERN)

        return formatValueWithPostfix(
            result.beforeLastChar(), " " + result.lastChar(),
            resProvider.getColor(R.color.titleGray)
        )
    }

    private fun formatMinPayment(value: Float): CharSequence = formatValueWithPostfix(
        value.format(FLOAT_PATTERN) + " ",
        coinLabel.toUpperCase(),
        resProvider.getColor(R.color.titleGray)
    )

    private fun formatPercent(prev: Float, now: Float): String {
        var percent = (((now - prev) / prev) * 100)
        if (percent < -1) percent = -percent

        return percent.format(FLOAT_PATTERN) + "%"
    }

    private fun formatPaymentTime(timeInterval: TimeIntervalDto): String {
        return timeInterval.from.formatTime() + "-" + timeInterval.to.formatTime()
    }

    private fun formatHashrate(value: Long): CharSequence {
        val result = formatLongValue(value, FLOAT_PATTERN)

        return formatValueWithPostfix(
            result.beforeLastChar() + " ",
            result.lastChar() + resProvider.getString(R.string.hashrate_per_second),
            resProvider.getColor(R.color.titleGray)
        )
    }
}



