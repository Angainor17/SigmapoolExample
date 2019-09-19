package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.common.managers.IChartManager
import com.sigmapool.common.models.ChartDto
import com.sigmapool.common.models.ManagerResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DashboardChartVM(manager: IChartManager) : ViewModel(){

    val xyValues = MutableLiveData<FloatArray>()
    val dates = MutableLiveData<List<Date>>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            handleChartData(manager.getChart())
        }
    }

    fun handleChartData(chartDto: ManagerResult<ChartDto>) = GlobalScope.launch (Dispatchers.Main) {
        if(chartDto.success){
            val datesbuf = chartDto.data!!.series.flatMap { listOf(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(it.time)) }
            var counter = 0
            val xybuf1 = chartDto.data!!.series.flatMap {
                listOf(
                    (counter++).toFloat(),
                    (it.hashrate).toFloat()
//                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parseit.time

                )
            }

            xyValues.postValue(xybuf1.toFloatArray())
            dates.postValue(datesbuf)
        } else{
            // TODO: error handling
        }
    }

}