package com.sigmapool.api.pool

import com.sigmapool.api.pool.models.CoinResponse
import com.sigmapool.api.pool.models.NetworkResponse
import com.sigmapool.api.pool.models.PaymentResponse
import com.sigmapool.api.pool.models.ProfitDailyResponse
import retrofit2.Retrofit

internal class PoolService(retrofit: Retrofit) : IPoolService {

    private val api = retrofit.create(PoolApi::class.java)

    override suspend fun getCoin(coin: String): CoinResponse = api.getCoin(coin).payload!!

    override suspend fun getPayment(coin: String): PaymentResponse = api.getPayment(coin).payload!!

    override suspend fun getNetwork(coin: String): NetworkResponse = api.getNetwork(coin).payload!!

    override suspend fun getProfitDaily(coin: String): ProfitDailyResponse = api.getProfitDaily(coin).payload!!
}