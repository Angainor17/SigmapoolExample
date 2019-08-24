package com.sigmapool.api.poolinfo

import com.sigmapool.api.models.PayloadModel
import com.sigmapool.api.models.PoolInfoBtc
import com.sigmapool.api.models.PoolInfoLtc
import retrofit2.http.GET

interface PoolInfoApi {

    @GET("api/v2/pool")
    suspend fun getBtcPoolInfo(): PayloadModel<PoolInfoBtc>

    @GET("api/v2/pool")
    suspend fun getLtcPoolInfo(): PayloadModel<PoolInfoLtc>

}