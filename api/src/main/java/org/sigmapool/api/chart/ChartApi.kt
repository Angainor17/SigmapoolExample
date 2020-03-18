package org.sigmapool.api.chart

import org.sigmapool.api.chart.models.ChartResponse
import org.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ChartApi {

    @GET("api/v2/{coin}/chart/hashrate")
    suspend fun getChart(
        @Path("coin") coin: String,
        @Query("period") period: String
    ): PayloadModel<ChartResponse>
}