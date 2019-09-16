package com.sigmapool.api.workers

import com.sigmapool.api.models.PayloadModel
import com.sigmapool.api.workers.models.WorkerResponseItem
import com.sigmapool.api.workers.models.WorkerStatusResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface WorkersApi {

    @GET("api/v2/{coin}/workers")
    suspend fun getWorkers(
        @Path("coin") coin: String,
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("status") status: String
    ): PayloadModel<ArrayList<WorkerResponseItem>>

    @GET("api/v2/{coin}/workers/status")
    suspend fun getStatus(
        @Path("coin") coin: String
    ): PayloadModel<WorkerStatusResponse>

}