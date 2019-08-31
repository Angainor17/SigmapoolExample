package com.sigmapool.app.screens.calculator.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.utils.interfaces.ViewState
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.NetworkDto
import com.sigmapool.common.models.ProfitDailyDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class CalcItemVM(
    val coinLabel: String
) : ViewModel() {

    private val poolManager by kodein.instance<IPoolManager>()

    val viewState = MutableLiveData(ViewState.LOADING)

    val price = MutableLiveData("~ \$ 368.48")//FIXME

    val currentPrice = MutableLiveData<CharSequence>("\$ 11 224.42")
    val difficulty = MutableLiveData<CharSequence>("7.93 T")
    val blockReward = MutableLiveData<CharSequence>("12.543 BTC")

    val clickAction = View.OnClickListener {
        Log.d("", "")
    }

    init {
        initValues()
    }

    fun initValues() {
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

    private fun initViews(data: CoinDto?, data1: NetworkDto?, data2: ProfitDailyDto?) {
        //TODO
    }
}