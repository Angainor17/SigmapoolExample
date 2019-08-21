package com.sigmapool.api.miners

import com.sigmapool.api.models.MinerListResponse
import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MinerApi {

    @GET("api/v2/miner/list")
    suspend fun getMiners(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int
    ): PayloadModel<MinerListResponse>
}