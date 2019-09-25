package com.sigmapool.common.managers

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.SeriesDto

const val PERIOD_HOUR = "hour"
const val PERIOD_DAY = "day"

interface IChartManager {

    suspend fun getChart(
        coin: String,
        period: String
    ): ManagerResult<ArrayList<SeriesDto>>
}