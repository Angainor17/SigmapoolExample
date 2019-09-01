package com.sigmapool.api.calculator

import com.sigmapool.api.calculator.models.CalcInfoResponse
import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET

internal interface CalcApi {

    @GET("api/v2/btc/text/calculator-page")
    suspend fun getCalcInfo(): PayloadModel<CalcInfoResponse>

}