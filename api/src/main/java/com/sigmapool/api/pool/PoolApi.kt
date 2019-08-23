package com.sigmapool.api.pool

import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET

internal interface PoolApi {

    @GET("api/v2/coin")
    suspend fun getCoin(): PayloadModel<CoinResponse>
}