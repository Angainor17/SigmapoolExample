package com.sigmapool.api.pool

import com.sigmapool.api.models.PayloadModel
import com.sigmapool.api.pool.models.CoinResponse
import com.sigmapool.api.pool.models.NetworkResponse
import com.sigmapool.api.pool.models.PaymentResponse
import com.sigmapool.api.pool.models.ProfitDailyResponse
import retrofit2.http.GET

internal interface PoolApi {

    @GET("api/v2/coin")
    suspend fun getCoin(): PayloadModel<CoinResponse>

    @GET("api/v2/payment")
    suspend fun getPayment(): PayloadModel<PaymentResponse>

    @GET("api/v2/network")
    suspend fun getNetwork(): PayloadModel<NetworkResponse>

    @GET("api/v2/profit/daily")
    suspend fun getProfitDaily(): PayloadModel<ProfitDailyResponse>
}