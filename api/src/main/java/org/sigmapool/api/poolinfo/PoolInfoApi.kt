package org.sigmapool.api.poolinfo

import org.sigmapool.api.models.CoinInfo
import org.sigmapool.api.models.DailyProfit
import org.sigmapool.api.models.PayloadModel
import org.sigmapool.common.models.PaymentDto
import org.sigmapool.common.models.SettlementDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PoolInfoApi {

    @GET("api/v2/{coin}/pool")
    suspend fun getPoolInfo(@Path("coin") coin: String): PayloadModel<CoinInfo>

    @GET("api/v2/{coin}/profit/daily")
    suspend fun getDailyProfit(@Path("coin") coin: String): PayloadModel<DailyProfit>

    @GET("api/v2/{coin}/payment")
    suspend fun getPayment(@Path("coin") coin: String): PayloadModel<PaymentDto>

    @GET("api/v2/{coin}/text/settlement-details")
    suspend fun getSettlementDetails(
        @Path("coin") coin: String,
        @Query("lang") lang: String
    ): PayloadModel<SettlementDetailsDto>
}