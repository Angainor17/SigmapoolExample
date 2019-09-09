package com.sigmapool.app.screens.calculator.viewModel

import android.graphics.Color
import android.text.SpannableString
import android.view.View
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.interfaces.ViewState.*
import com.sigmapool.app.utils.liveDataZip
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.NetworkDto
import com.sigmapool.common.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class CalcItemVM(
    private val coinLabel: String
) : ViewModel() {

    private val res by kodein.instance<IResProvider>()
    private val poolManager by kodein.instance<IPoolManager>()
    private val currencyProvider by kodein.instance<ICurrencyProvider>()

    private val converterViewState = MutableLiveData(LOADING)
    private val generalInfoViewState = MutableLiveData(LOADING)

    val refreshing = liveDataZip(converterViewState, generalInfoViewState)
    { converterVS, generalVS -> converterVS == LOADING || generalVS == LOADING }

    val price = MutableLiveData("~ \$ 368.48")//FIXME
    val currentPrice = MutableLiveData<CharSequence>(formatCurrentPrice(0f))
    val difficulty = MutableLiveData<CharSequence>(formatDifficulty(0L))
    val blockReward = MutableLiveData<CharSequence>(formatBlockReward(0f))

    val topLine = CalcValueVM(res.getString(R.string.hashrate), res.getString(R.string.thashs_per_second), "0")
    val bottomLine = CalcValueVM(res.getString(R.string.profit), res.getString(R.string.btc_per_day), "0.0", "~ \$ 368.48")

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
            //TODO implement
            converterViewState.postValue(CONTENT)
        } else {
            converterViewState.postValue(ERROR)
        }
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

    private fun initViews(coin: CoinDto?, network: NetworkDto?) {
        currentPrice.postValue(formatCurrentPrice(coin?.price ?: 0f))
        difficulty.postValue(formatDifficulty(network?.networkDifficulty ?: 0L))
        blockReward.postValue(formatBlockReward(network?.blockReward ?: 0f))
    }

    private fun formatCurrentPrice(value: Float): SpannableString {
        return formatValueWithPrefix(
            " " + value.toInt().format(FLOAT_PATTERN),
            currencyProvider.getSymbol().toString(),
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