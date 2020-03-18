package org.sigmapool.api.dashboard

import org.sigmapool.api.dashboard.models.AvgResponse
import org.sigmapool.api.dashboard.models.SubaccountsResponse
import org.sigmapool.api.models.PayloadModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DashboardApi {

    @GET("api/v2/{coin}/user/subAccounts")
    suspend fun subaccounts(
        @Path("coin") coin: String
    ): PayloadModel<SubaccountsResponse>

    @GET("api/v2/{coin}/user/hashrate")
    suspend fun avg(
        @Path("coin") coin: String
    ): PayloadModel<AvgResponse>
}