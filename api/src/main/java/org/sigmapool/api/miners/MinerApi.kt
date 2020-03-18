package org.sigmapool.api.miners

import org.sigmapool.api.models.MinerListResponse
import org.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MinerApi {

    @GET("api/v2/miner/list")
    suspend fun getMiners(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int
    ): PayloadModel<MinerListResponse>
}