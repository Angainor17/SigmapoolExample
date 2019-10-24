package org.sigmapool.sigmapool.screens.calculator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import org.kodein.di.generic.instance
import org.sigmapool.common.viewModels.ITitleViewModel
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.screens.calculator.ICalculatorFragmentModel
import org.sigmapool.sigmapool.screens.calculator.params.CalcItemParams

import org.sigmapool.sigmapool.utils.liveDataZip

class CalculatorVM(val view: ICalculatorFragmentModel) : ViewModel(), ITitleViewModel {

    private val resProvider by kodein.instance<IResProvider>()
    private val coinProvider by kodein.instance<ICoinProvider>()

    private val refreshingInfo = MutableLiveData(false)

    val calcItems: LiveData<List<CalcItemVM>> = coinProvider.coins.map {
        it.map {
            val item = CalcItemVM(CalcItemParams(it.text, it.unit))
            item.onRefresh()
            item
        }
    }
    val info = MutableLiveData("")
    val tabPositionLiveData = MutableLiveData(ViewPagerScreen(0))

    val refreshing = liveDataZip(isScreensRefreshing(), refreshingInfo)
    { screenLoading, infoLoading -> screenLoading || infoLoading }

    val coinVM = CoinTabVM(tabPositionLiveData)

    override fun getTitle() = MutableLiveData(resProvider.getString(R.string.calculator))

    init {
        onRefresh()
        tabPositionLiveData.observeForever {
            info.postValue(calcItems.value?.get(it.position)?.infoText ?: "")
        }
    }

    private fun isScreensRefreshing(): LiveData<Boolean> =
        try {
            liveDataZip(calcItems.value?.map { it.refreshing }
                ?: ArrayList()) { items -> items.any { it } }
        } catch (e: Exception) {
            MutableLiveData(false)
        }

    fun onRefresh() {
        calcItems.value?.forEach {
            it.onRefresh()
        }
    }
}