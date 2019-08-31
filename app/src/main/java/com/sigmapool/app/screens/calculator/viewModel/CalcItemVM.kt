package com.sigmapool.app.screens.calculator.viewModel

import android.graphics.Color
import android.text.SpannableString
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.utils.*
import com.sigmapool.app.utils.interfaces.ViewState
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.NetworkDto
import com.sigmapool.common.models.ProfitDailyDto
import com.sigmapool.common.utils.FLOAT_PATTERN
import com.sigmapool.common.utils.format
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class CalcItemVM(
    private val coinLabel: String
) : ViewModel() {

    private val poolManager by kodein.instance<IPoolManager>()
    private val currencyProvider by kodein.instance<ICurrencyProvider>()

    val viewState = MutableLiveData(ViewState.LOADING)

    val price = MutableLiveData("~ \$ 368.48")//FIXME

    val currentPrice = MutableLiveData<CharSequence>(formatCurrentPrice(11224.42f))//FIXME
    val difficulty = MutableLiveData<CharSequence>(formatDifficulty(793213123123123L))//FIXME
    val blockReward = MutableLiveData<CharSequence>(formatBlockReward(12.543f))//FIXME

    val clickAction = View.OnClickListener {
        Log.d("", "")//TODO implement
    }

    init {
        initValues()
    }

    private fun initValues() {
        GlobalScope.launch(Dispatchers.IO) {
            val coinDto = poolManager.getCoin(coinLabel)
            val networkDto = poolManager.getNetwork(coinLabel)
            val profitDailyDto = poolManager.getProfitDaily(coinLabel)

            if (coinDto.success && networkDto.success && profitDailyDto.success) {
                initViews(coinDto.data, networkDto.data, profitDailyDto.data)
                viewState.postValue(ViewState.CONTENT)
            } else {
                viewState.postValue(ViewState.ERROR)
            }
        }
    }

    private fun initViews(coin: CoinDto?, network: NetworkDto?, profitDaily: ProfitDailyDto?) {
        currentPrice.postValue(formatCurrentPrice(11224.42f))//FIXME
        difficulty.postValue(formatDifficulty(793213123123123L))//FIXME
        blockReward.postValue(formatBlockReward(12.543f))//FIXME
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
            result.lastChar(),
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