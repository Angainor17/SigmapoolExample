package com.sigmapool.api.poolinfo

import com.sigmapool.api.models.PoolInfoBtc



internal interface IPoolInfoService {
    suspend fun getPoolInfo(): PoolInfoBtc?
}

/*
internal interface IPoolInfoService {

    @FormUrlEncoded
    @POST("api/v2/pool")
    suspend fun getPoolInfo(
        ): PayloadModel<AuthResult>
*/