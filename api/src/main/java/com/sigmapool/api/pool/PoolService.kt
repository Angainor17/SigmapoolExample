package com.sigmapool.api.pool

import retrofit2.Retrofit

internal class PoolService(retrofit: Retrofit) : IPoolService {

    private val api = retrofit.create(PoolApi::class.java)

    override suspend fun getCoin(): CoinResponse {
        return api.getCoin().payload!!
    }
}