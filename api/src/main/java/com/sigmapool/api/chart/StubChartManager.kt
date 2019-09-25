package com.sigmapool.api.chart

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IChartManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.SeriesDto
import kotlinx.coroutines.delay
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

internal class StubChartManager(serviceProvider: IApiServiceProvider) : IChartManager {

    override suspend fun getChart(
        coin: String,
        period: String
    ): ManagerResult<ArrayList<SeriesDto>> {
        delay(4000)

        return ManagerResult(
            ArrayList(List(40) {
                SeriesDto(
                    Date(Date().time + it * 24 * 60 * 60 * 1000),
                    ((if (it % 2 == 0) 1 else -1) * Random.nextInt(10) * 10000000000000000f) + 144316594616604000f
                )
            }
            ))
    }
}
