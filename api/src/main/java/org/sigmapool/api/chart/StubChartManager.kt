package org.sigmapool.api.chart

import kotlinx.coroutines.delay
import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.common.managers.IChartManager
import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.SeriesDto
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.random.Random

internal class StubChartManager(serviceProvider: IApiServiceProvider) : IChartManager {

    override suspend fun getChart(
        coin: String,
        period: String
    ): ManagerResult<ArrayList<SeriesDto>> {
        delay(2000)

        return ManagerResult(
            ArrayList(List(40) {
                SeriesDto(
                    Date(Date().time + it * 24 * 60 * 60 * 1000),
                    abs((Random.nextInt(10) * 10f) + 14f),
                    abs((Random.nextInt(10) * 10) + 13f)
                )
            }
            ))
    }

    override suspend fun getUserChart(
        coin: String,
        period: String
    ): ManagerResult<ArrayList<SeriesDto>> {
        delay(2000)

        return ManagerResult(
            ArrayList(List(40) {
                SeriesDto(
                    Date(Date().time + it * 24 * 60 * 60 * 1000),
                    484141253280990f,
                    2500000f
                )
            }
            ))
    }
}
