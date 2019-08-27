package com.sigmapool.api.poolinfo

import com.sigmapool.api.models.DailyProfitBtc
import com.sigmapool.api.models.PayloadModel
import com.sigmapool.api.models.PoolInfoBtc
import com.sigmapool.api.models.PoolInfoLtc
import retrofit2.http.GET
import retrofit2.http.Path

interface PoolInfoApi {

    @GET("api/v2/btc/pool")
    suspend fun getBtcPoolInfo(): PayloadModel<PoolInfoBtc>

    //TODO: through path parameter, make one function for btc/ltc
    @GET("api/v2/{coin}/profit/daily")
    suspend fun getDailyProfit(@Path("coin") coin:String): PayloadModel<DailyProfitBtc>

    @GET("api/v2/ltc/pool")
    suspend fun getLtcPoolInfo(): PayloadModel<PoolInfoLtc>

}