package org.sigmapool.api.workers

import org.sigmapool.api.models.PayloadModel
import org.sigmapool.api.workers.models.WorkerListResponse
import org.sigmapool.api.workers.models.WorkerStatusResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface WorkersApi {

    @GET("api/v2/{coin}/workers/list")
    suspend fun getWorkers(
        @Path("coin") coin: String,
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("status") status: String
    ): PayloadModel<WorkerListResponse>

    @GET("api/v2/{coin}/workers/status")
    suspend fun getStatus(
        @Path("coin") coin: String
    ): PayloadModel<WorkerStatusResponse>

}