package org.sigmapool.sigmapool.screens.calculator.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.ICalcManager
import org.sigmapool.common.viewModels.ITitleViewModel
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.lang.ILocaleProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.screens.calculator.ICalculatorFragmentModel
import org.sigmapool.sigmapool.screens.calculator.params.CalcItemParams
import org.sigmapool.sigmapool.screens.home.coin.BTC
import org.sigmapool.sigmapool.screens.home.coin.LTC
import org.sigmapool.sigmapool.utils.liveDataZip

class CalculatorVM(val view: ICalculatorFragmentModel) : ViewModel(), ITitleViewModel {

    private val btcCalcItem =
        CalcItemVM(CalcItemParams(BTC, R.string.t_hashs_per_sec, 1000000000000L))
    private val ltcCalcItem = CalcItemVM(CalcItemParams(LTC, R.string.m_hashs_per_sec, 1000000L))
    private val refreshingInfo = MutableLiveData(false)

    val calcItems = arrayListOf(btcCalcItem, ltcCalcItem)
    val info = MutableLiveData("")
    val tabPositionLiveData = MutableLiveData(ViewPagerScreen(0))

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