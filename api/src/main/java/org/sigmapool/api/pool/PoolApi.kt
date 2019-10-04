package org.sigmapool.api.pool

import org.sigmapool.api.models.PayloadModel
import org.sigmapool.api.pool.models.*
import retrofit2.http.*

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

    @GET("api/v2/{coin}/user/settings/payout/scheme")
    suspend fun getScheme(
        @Path("coin") coin: String
    ): PayloadModel<SchemeResponse>

    @FormUrlEncoded
    @POST("api/v2/{coin}/user/settings/payout/scheme")
    suspend fun setScheme(
        @Path("coin") coin: String,
        @Field("scheme") scheme: String
    ): PayloadModel<SchemeResponse>

    @GET("api/v2/{coin}/user/settings/payout/threshold")
    suspend fun getThreshold(
        @Path("coin") coin: String
    ): PayloadModel<ThresholdResponse>

    @FormUrlEncoded
    @POST("api/v2/{coin}/user/settings/payout/threshold")
    suspend fun setThreshold(
        @Path("coin") coin: String,
        @Field("threshold") threshold: Float
    ): PayloadModel<ThresholdResponse>
}