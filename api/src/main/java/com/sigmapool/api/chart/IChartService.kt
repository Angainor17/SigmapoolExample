package com.sigmapool.api.chart

import com.sigmapool.api.models.ChartModel



internal interface IChartService {
    suspend fun getChart(): ChartModel?
}

/*
internal interface IPoolInfoService {

    @FormUrlEncoded
    @POST("api/v2/pool")
    suspend fun getPoolInfo(
        ): PayloadModel<AuthResult>
*/