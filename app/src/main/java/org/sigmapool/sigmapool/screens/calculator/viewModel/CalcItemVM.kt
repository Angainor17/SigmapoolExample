package org.sigmapool.sigmapool.screens.calculator.viewModel

import android.graphics.Color
import android.text.SpannableString
import android.view.View
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.api.kodein.AUTH_MODE
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.models.CoinInfoDto
import org.sigmapool.common.models.NetworkDto
import org.sigmapool.common.models.ProfitDailyDto
import org.sigmapool.common.utils.*
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.currency.ICurrencyProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.calculator.params.CalcItemParams
import org.sigmapool.sigmapool.utils.interfaces.OnTextWatcherVm
import org.sigmapool.sigmapool.utils.interfaces.ViewState.*
import org.sigmapool.sigmapool.utils.liveDataZip


class CalcItemVM(
    val params: CalcItemParams
) : ViewModel(), OnTextWatcherVm {

    private val coinLabel = params.coinLabel

    private val res by kodein.instance<IResProvider>()
    private val poolManager by kodein.instance<IPoolManager>(AUTH_MODE)
    private val currencyProvider by kodein.instance<ICurrencyProvider>()
    private val converterViewState = MutableLiveData(LOADING)

    private val generalInfoViewState = MutableLiveData(LOADING)
    val refreshing = liveDataZip(converterViewState, generalInfoViewState)
    { converterVS, generalVS -> converterVS == LOADING || generalVS == LOADING }

    val currentPrice = MutableLiveData<CharSequence>(formatCurrentPrice(0f))
    val difficulty = MutableLiveData<CharSequence>(formatDifficulty(0L))
    val blockReward = MutableLiveData<CharSequence>(formatBlockReward(0f))
    val topLine = CalcValueVM(
        res.getString(R.string.hashrate),
        params.hashLabel,
        ""
    )

    val bottomLine = CalcValueVM(
        res.getString(R.string.profit),
        coinLabel.toUpperCase() + res.getString(R.string.per_day),
        "",
        formatPrice(0.0f)
    )

    private fun formatPrice(price: Float) =
        "~ " + currencyProvider.getSymbol() + " " + price.format(FLOAT_PATTERN).replace(',', '.')

    private var profitDaily: ProfitDailyDto? = null

    var isHashrateToProfit = true
    val changeStateAction = View.OnClickListener { switchCalcValueLines() }

    fun onRefresh() {
        initValues()
    }

    private fun initValues() {
        GlobalScope.launch(Dispatchers.IO) {
            initGeneralInfo()
            initConverter()
        }
    }

    private fun switchCalcValueLines() {
        isHashrateToProfit = !isHashrateToProfit

        val label = topLine.label.value
        val postfix = topLine.postfix.value
        val value = topLine.value.value
        val price = topLine.price.value

        topLine.label.postValue(bottomLine.label.value)
        topLine.postfix.postValue(bottomLine.postfix.value)
        topLine.value.postValue(bottomLine.value.value)
        topLine.price.postValue(bottomLine.price.value)

        bottomLine.label.postValue(label)
        bottomLine.postfix.postValue(postfix)
        bottomLine.value.postValue(value)
        bottomLine.price.postValue(price)
    }

    private suspend fun initConverter() {
        val profitDailyDto = poolManager.getProfitDaily(coinLabel)

        if (profitDailyDto.success) {
            profitDaily = profitDailyDto.data
            converterViewState.postValue(CONTENT)
        } else {
            converterViewState.postValue(ERROR)
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        convertValue(s)
    }

    private fun convertValue(s: CharSequence) {
        profitDaily?.let {
            val value = try {
                s.toString().replace(',', '.').toFloat()
            } catch (e: Exception) {
                0f
            }

            val calculatedValue = if (isHashrateToProfit) {
                value * it.profit
            } else {
                value / it.profit
            }

            bottomLine.value.postValue(calculatedValue.trimZeroEnd().replace(',', '.'))

            updatePrice(
                if (isHashrateToProfit) bottomLine else topLine,
                if (isHashrateToProfit) calculatedValue else value
            )
        }
    }

    private fun updatePrice(vm: CalcValueVM, calculatedValue: Float) {
        vm.price.postValue(formattedPrice(calculatedValue).replace(',', '.'))
    }

    private fun formattedPrice(coinValue: Float): String {
        return "~ " + currencyProvider.getSymbol() + " " +
                currencyProvider.fromCoinToCurrency(coinLabel, coinValue).format(FLOAT_PATTERN)
    }

    private suspend fun initGeneralInfo() {
        generalInfoViewState.postValue(LOADING)

        val coinDto = poolManager.getCoin(coinLabel)
        val networkDto = poolManager.getNetwork(coinLabel)

        if (coinDto.success && networkDto.success) {
            initViews(coinDto.data, networkDto.data)
            generalInfoViewState.postValue(CONTENT)
        } else {
            generalInfoViewState.postValue(ERROR)
        }
    }

    private fun initViews(coin: CoinInfoDto?, network: NetworkDto?) {
        currentPrice.postValue(formatCurrentPrice(coin?.price ?: 0f))
        difficulty.postValue(formatDifficulty(network?.networkDifficulty?.toLong() ?: 0L))
        blockReward.postValue(formatBlockReward(network?.blockReward ?: 0f))
    }

    private fun formatCurrentPrice(value: Float): SpannableString {
        return formatValueWithPrefix(
            " " + value.toInt().format(FLOAT_PATTERN),
            currencyProvider.getSymbol(),
            darkGray()
        )
    }

    private fun formatDifficulty(value: Long): SpannableString {
        val result = formatLongValue(value, FLOAT_PATTERN)

        return formatValueWithPostfix(
            result.beforeLastChar() + " ",
            if (result.lastChar().isDigitsOnly()) "" else result.lastChar(),
            darkGray()
        )
    }

    private fun formatBlockReward(value: Float): SpannableString {
        return formatValueWithPostfix(
            value.format(FLOAT_PATTERN) + " ",
            coinLabel.toUpperCase(),
            darkGray()
        )
    }

    private fun darkGray() = Color.rgb(133, 133, 133)
}