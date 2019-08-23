package com.sigmapool.api.coin

import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET

internal interface CoinApi {

    @GET("api/v2/coin")
    suspend fun getCoin(): PayloadModel<CoinResponse>
}