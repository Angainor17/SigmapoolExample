package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.customViews.CustomSwitchVm
import com.sigmapool.app.utils.customViews.OnSwitchSelected
import com.sigmapool.common.managers.PERIOD_DAY
import com.sigmapool.common.managers.PERIOD_HOUR
import com.sigmapool.common.models.SeriesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class DashboardChartVM : ViewModel() {

    val resProvider by kodein.instance<IResProvider>()

    private val hourData = ArrayList<SeriesDto>()
    private val dayData = ArrayList<SeriesDto>()

    val chartData = MutableLiveData<ArrayList<SeriesDto>>(hourData)
    val yAxisLabel = MutableLiveData(resProvider.getString(R.string.hashrate_per_second))

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