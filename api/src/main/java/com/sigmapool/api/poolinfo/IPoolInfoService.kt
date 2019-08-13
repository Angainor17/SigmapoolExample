package com.sigmapool.api.poolinfo

import com.sigmapool.api.models.PoolInfo



internal interface IPoolInfoService {
    suspend fun getPoolInfo(): PoolInfo?
}

/*
internal interface IPoolInfoService {

    @FormUrlEncoded
    @POST("api/v2/pool")
    suspend fun getPoolInfo(
        ): PayloadModel<AuthResult>
*/