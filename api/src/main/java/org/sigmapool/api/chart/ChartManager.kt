package org.sigmapool.api.chart

import org.sigmapool.api.poolinfo.wrapManagerResult
import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.common.managers.IChartManager
import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.SeriesDto

internal class ChartManager(serviceProvider: IApiServiceProvider) : IChartManager {

    private val chartService = serviceProvider.create(ChartApi::class.java)

    override suspend fun getChart(
        coin: String,
        period: String
    ): ManagerResult<ArrayList<SeriesDto>> {
        return wrapManagerResult {

            val chart = this.chartService.getChart(coin.toLowerCase(), period).payload!!

            ArrayList(chart.series.map {
                SeriesDto(it.time, it.hashrate)
            })
        }
    }
}
