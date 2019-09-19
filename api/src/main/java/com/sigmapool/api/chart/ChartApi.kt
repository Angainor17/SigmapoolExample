package com.sigmapool.api.chart

import com.sigmapool.api.models.PayloadModel
import com.sigmapool.common.models.ChartDto
import retrofit2.http.GET

interface ChartApi {

    //TODO: cehardcode coin into parameter
    @GET("api/v2/btc/chart/hashrate")
   suspend fun getChart(): PayloadModel<ChartDto>
//   @GET("api/v2/{coin}/profit/daily")
 //   suspend fun getDailyProfit(@Path("coin") coin:String): PayloadModel<DailyProfitBtc>


}