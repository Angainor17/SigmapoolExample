package com.sigmapool.api.chart

import com.sigmapool.api.poolinfo.wrapManagerResult
import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IChartManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.SeriesDto

internal class ChartManager(serviceProvider: IApiServiceProvider) : IChartManager {

    private val chartService = serviceProvider.create(ChartApi::class.java)

    override suspend fun getChart(
        coin: String,
        period: String
    ): ManagerResult<ArrayList<SeriesDto>> {
        return wrapManagerResult {

            val chart = this.chartService.getChart(coin, period).payload!!

            ArrayList(chart.series.map {
                SeriesDto(it.time, it.hashrate)
            })
        }
    }
}
