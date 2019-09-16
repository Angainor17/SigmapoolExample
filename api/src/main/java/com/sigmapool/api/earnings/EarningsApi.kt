package com.sigmapool.api.earnings

import com.sigmapool.api.earnings.models.*
import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface EarningsApi {

    @GET("api/v2/{coin}/user/earnings/daily")
    suspend fun earningsDaily(
        @Path("coin") coin: String
    ): PayloadModel<EarningsResponse>

    @GET("api/v2/{coin}/user/total-paid")
    suspend fun totalPaid(
        @Path("coin") coin: String
    ): PayloadModel<TotalPaidResponse>

    @GET("api/v2/{coin}/user/balance")
    suspend fun balance(
        @Path("coin") coin: String
    ): PayloadModel<BalanceResponse>

    @GET("api/v2/{coin}/user/payments")
    suspend fun payments(
        @Path("coin") coin: String,
        @Query("page") page: Int
    ): PayloadModel<PaymentListResponse>

    @GET("api/v2/{coin}/user/last-payment")
    suspend fun getLastPayment(
        @Path("coin") coin: String
    ): PayloadModel<LastPaymentResponse>

    @GET("api/v2/{coin}/user/estimated-profit")
    suspend fun getEstimatedProfit(
        @Path("coin") coin: String
    ): PayloadModel<EstimatedProfitResponse>

    @GET("api/v2/{coin}/user/address")
    suspend fun address(
        @Path("coin") coin: String
    ): PayloadModel<AddressResponse>
}