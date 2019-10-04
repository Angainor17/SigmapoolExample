package org.sigmapool.sigmapool.screens.home.coin

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IChartManager
import org.sigmapool.common.managers.PERIOD_HOUR
import org.sigmapool.common.models.*
import org.sigmapool.common.utils.*
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.currency.ICurrencyProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.utils.interfaces.StateVM
import org.sigmapool.sigmapool.utils.interfaces.ViewState
import kotlin.math.abs

class CoinItemVM(val coinLabel: String, @DrawableRes val iconRes: Int) : ViewModel(),
    StateVM {

    private val resProvider by kodein.instance<IResProvider>()
    private val currencyProvider by kodein.instance<ICurrencyProvider>()
    private val chartManager by kodein.instance<IChartManager>()

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

    val chartData = MutableLiveData(ArrayList<SeriesDto>())

    init {
        refreshChartData()
    }

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
        val percent = (((now - prev) / prev) * 100)
        return abs(percent).format(FLOAT_PATTERN) + "%"
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

    private fun handleChartData(chartDto: ManagerResult<ArrayList<SeriesDto>>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (chartDto.success) {
                chartData.postValue(chartDto.data)
            } else {
                chartData.postValue(ArrayList())
            }
        }

    private fun refreshChartData() {
        GlobalScope.launch(Dispatchers.Default) {
            handleChartData(
                chartManager.getChart(coinLabel.toLowerCase(), PERIOD_HOUR)
            )
        }
    }
}



