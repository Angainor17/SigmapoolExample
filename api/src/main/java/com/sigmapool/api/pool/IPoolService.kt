package com.sigmapool.api.pool

import com.sigmapool.api.pool.models.CoinResponse
import com.sigmapool.api.pool.models.NetworkResponse
import com.sigmapool.api.pool.models.PaymentResponse

internal interface IPoolService {

    suspend fun getCoin(): CoinResponse
    suspend fun getPayment(): PaymentResponse
    suspend fun getNetwork(): NetworkResponse
}

