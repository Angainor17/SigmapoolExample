package com.sigmapool.api.poolinfo

import com.sigmapool.api.models.PayloadModel
import com.sigmapool.api.models.PoolInfo
import retrofit2.http.GET

interface PoolInfoApi {

    @GET("api/v2/pool")
    suspend fun getPoolInfo(): PayloadModel<PoolInfo>

}