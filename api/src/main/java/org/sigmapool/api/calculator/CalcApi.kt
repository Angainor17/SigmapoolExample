package org.sigmapool.api.calculator

import org.sigmapool.api.calculator.models.CalcInfoResponse
import org.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CalcApi {

    @GET("api/v2/{coin}/text/calculator-page")
    suspend fun getCalcInfo(
        @Path("coin") coin: String,
        @Query("lang") lang: String
    ): PayloadModel<CalcInfoResponse>
}