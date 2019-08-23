package com.sigmapool.api.pool

import com.sigmapool.api.pool.models.CoinResponse
import com.sigmapool.api.pool.models.NetworkResponse
import com.sigmapool.api.pool.models.PaymentResponse
import retrofit2.Retrofit

internal class PoolService(retrofit: Retrofit) : IPoolService {

    private val api = retrofit.create(PoolApi::class.java)

    override suspend fun getCoin(): CoinResponse = api.getCoin().payload!!

    override suspend fun getPayment(): PaymentResponse = api.getPayment().payload!!

    override suspend fun getNetwork(): NetworkResponse = api.getNetwork().payload!!
}