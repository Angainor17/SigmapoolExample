package com.sigmapool.api.pool

internal interface IPoolService {

    suspend fun getCoin(): CoinResponse
}

