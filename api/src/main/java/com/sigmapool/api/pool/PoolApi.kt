package com.sigmapool.api.pool

import com.sigmapool.api.models.PayloadModel
import com.sigmapool.api.pool.models.CoinResponse
import com.sigmapool.api.pool.models.NetworkResponse
import com.sigmapool.api.pool.models.PaymentResponse
import com.sigmapool.api.pool.models.ProfitDailyResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PoolApi {

    @GET("api/v2/{coin}/coin")
    suspend fun getCoin(
        @Path("coin") coin: String
    ): PayloadModel<CoinResponse>

    @GET("api/v2/{coin}/payment")
    suspend fun getPayment(
        @Path("coin") coin: String
    ): PayloadModel<PaymentResponse>

    @GET("api/v2/{coin}/network")
    suspend fun getNetwork(
        @Path("coin") coin: String
    ): PayloadModel<NetworkResponse>

    @GET("api/v2/{coin}/profit/daily")
    suspend fun getProfitDaily(
        @Path("coin") coin: String
    ): PayloadModel<ProfitDailyResponse>
}