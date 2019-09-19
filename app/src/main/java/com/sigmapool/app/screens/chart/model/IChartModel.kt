package com.sigmapool.app.screens.chart

import com.sigmapool.common.models.ChartDto
import com.sigmapool.common.models.ManagerResult

interface IChartModel {
    suspend fun getChart(): ManagerResult<ChartDto>
}
