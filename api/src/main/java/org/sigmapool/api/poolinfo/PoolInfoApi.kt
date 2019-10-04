package org.sigmapool.api.poolinfo

import org.sigmapool.api.models.DailyProfitBtc
import org.sigmapool.api.models.PayloadModel
import org.sigmapool.api.models.PoolInfoBtc
import org.sigmapool.api.models.PoolInfoLtc
import org.sigmapool.common.models.PaymentDto
import org.sigmapool.common.models.SettlementDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PoolInfoApi {

    @GET("api/v2/btc/pool")
    suspend fun getBtcPoolInfo(): PayloadModel<PoolInfoBtc>

    //TODO: through path parameter, make one function for btc/ltc
    @GET("api/v2/{coin}/profit/daily")
    suspend fun getDailyProfit(@Path("coin") coin: String): PayloadModel<DailyProfitBtc>

    @GET("api/v2/ltc/pool")
    suspend fun getLtcPoolInfo(): PayloadModel<PoolInfoLtc>

    @GET("api/v2/{coin}/payment")
    suspend fun getPayment(@Path("coin") coin: String): PayloadModel<PaymentDto>

    @GET("api/v2/{coin}/text/settlement-details")
    suspend fun getSettlementDetails(
        @Path("coin") coin: String,
        @Query("lang") lang: String
    ): PayloadModel<SettlementDetailsDto>
}