package com.sigmapool.common.managers

import com.sigmapool.common.models.ChartDto
import com.sigmapool.common.models.ManagerResult


interface IChartManager {
    suspend fun getChart(): ManagerResult<ChartDto>
}