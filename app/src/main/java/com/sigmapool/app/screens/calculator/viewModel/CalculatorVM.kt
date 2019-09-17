package com.sigmapool.app.screens.calculator.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.lang.ILocaleProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.calculator.ICalculatorFragmentModel
import com.sigmapool.app.screens.calculator.params.CalcItemParams
import com.sigmapool.app.screens.home.coin.BTC
import com.sigmapool.app.screens.home.coin.LTC
import com.sigmapool.app.utils.liveDataZip
import com.sigmapool.common.managers.ICalcManager
import com.sigmapool.common.viewModels.ITitleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class CalculatorVM(val view: ICalculatorFragmentModel) : ViewModel(), ITitleViewModel {

    private val btcCalcItem = CalcItemVM(CalcItemParams(BTC, R.string.t_hashs_per_sec, 1000000000000L))
    private val ltcCalcItem = CalcItemVM(CalcItemParams(LTC, R.string.m_hashs_per_sec, 1000000L))
    private val refreshingInfo = MutableLiveData(false)

    val calcItems = arrayListOf(btcCalcItem, ltcCalcItem)
    val info = MutableLiveData("")
    val tabPositionLiveData = MutableLiveData<Int>()

    val refreshing = liveDataZip(btcCalcItem.refreshing, ltcCalcItem.refreshing, refreshingInfo)
    { btcLoading, ltcLoading, infoLoading -> btcLoading || ltcLoading || infoLoading }

    val calculatorTabVM = CoinTabVM(tabPositionLiveData)

    private val resProvider by kodein.instance<IResProvider>()
    private val calcManager by kodein.instance<ICalcManager>()
    private val localeProvider by kodein.instance<ILocaleProvider>()

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.calculator))

    init {
        onRefresh()
    }

    fun onRefresh() {
        initRefreshInfo()
        calcItems.forEach { it.onRefresh() }
    }

    private fun initRefreshInfo() {
        refreshingInfo.postValue(true)

        GlobalScope.launch(Dispatchers.IO) {
            val result = calcManager.getCalcInfo(localeProvider.getLocale().locale)
            info.postValue(result.data?.calculatorText ?: "")
            refreshingInfo.postValue(false)
        }
    }
}