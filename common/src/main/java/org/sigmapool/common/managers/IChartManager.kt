package org.sigmapool.common.managers

import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.SeriesDto

const val PERIOD_HOUR = "hour"
const val PERIOD_DAY = "day"

interface IChartManager {

    suspend fun getChart(
        coin: String,
        period: String
    ): ManagerResult<ArrayList<SeriesDto>>
}