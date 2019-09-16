package com.sigmapool.api.dashboard

import com.sigmapool.api.dashboard.models.SubaccountsResponse
import com.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DashboardApi {

    @GET("api/v2/{coin}/user/subaccounts")
    suspend fun subaccounts(
        @Path("coin") coin: String
    ): PayloadModel<SubaccountsResponse>
}