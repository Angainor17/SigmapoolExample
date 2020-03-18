package org.sigmapool.sigmapool.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.PERIOD_DAY
import org.sigmapool.common.managers.PERIOD_HOUR
import org.sigmapool.common.models.SeriesDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.utils.customViews.CustomSwitchVm
import org.sigmapool.sigmapool.utils.customViews.OnSwitchSelected

class DashboardChartVM {

    val resProvider by kodein.instance<IResProvider>()

    private val hourData = ArrayList<SeriesDto>()
    private val dayData = ArrayList<SeriesDto>()

    val chartData = MutableLiveData<ArrayList<SeriesDto>>(hourData)
    val yAxisLabel = MutableLiveData("T" + resProvider.getString(R.string.hashrate_per_second))

    val customSwitchVm = CustomSwitchVm(
        resProvider.getString(R.string.one_hour),
        resProvider.getString(R.string.one_day),
        R.color.chart_switcher_text_color,
        R.drawable.chart_switcher_left_bg,
        R.drawable.chart_switcher_bg,
        R.drawable.chart_switcher_bg_unselected
    )

    var chartMode: String = PERIOD_HOUR

    init {
        customSwitchVm.clickListener = object : OnSwitchSelected {
            override fun onSelected(leftSelected: Boolean) {
                chartMode = if (leftSelected) PERIOD_HOUR else PERIOD_DAY
                renderChartData()
            }
        }
    }

    fun onStart(){
        customSwitchVm.refreshBg()
    }

    fun initHourData(newData: ArrayList<SeriesDto>) {
        hourData.clear()
        hourData.addAll(newData)

        if (chartMode == PERIOD_HOUR) {
            renderChartData()
        }
    }

    fun initDayData(newData: ArrayList<SeriesDto>) {
        dayData.clear()
        dayData.addAll(newData)

        if (chartMode == PERIOD_DAY) {
            renderChartData()
        }
    }

    private fun renderChartData() {
        GlobalScope.launch(Dispatchers.Main) {
            chartData.postValue(
                if (chartMode == PERIOD_HOUR) hourData else dayData
            )
        }
    }
}