package com.sigmapool.api.chart


import com.sigmapool.api.poolinfo.wrapManagerResult
import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IChartManager
import com.sigmapool.common.models.ChartDto
import com.sigmapool.common.models.ManagerResult


internal class ChartManager(serviceProvider: IApiServiceProvider) : IChartManager {

    private val chartService = serviceProvider.create(ChartApi::class.java)

    override suspend fun getChart(): ManagerResult<ChartDto> {
        return wrapManagerResult {

            val chart = this.chartService.getChart().payload!!

            val chartDto = ChartDto(
                chart.series
            )

            chartDto
        }
    }

}
