package com.sigmapool.api.pool

import com.sigmapool.api.models.PayloadModel
import com.sigmapool.api.pool.models.*
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

    @GET("api/v2/{coin}/currency")
    suspend fun getCurrency(
        @Path("coin") coin: String
    ): PayloadModel<CurrencyResponse>
}