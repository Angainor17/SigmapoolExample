package com.sigmapool.api.calculator

import com.sigmapool.api.calculator.models.CalcInfoResponse
import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CalcApi {

    @GET("api/v2/btc/text/calculator-page")
    suspend fun getCalcInfo(
        @Query("lang") lang: String
    ): PayloadModel<CalcInfoResponse>
}