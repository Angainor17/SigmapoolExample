package com.sigmapool.api.pool

import com.sigmapool.api.pool.models.*
import com.sigmapool.api.providers.IApiServiceProvider

internal class PoolService(apiProvider: IApiServiceProvider) : IPoolService {

    private val api = apiProvider.create(PoolApi::class.java)

    override suspend fun getCoin(coin: String): CoinResponse = api.getCoin(coin).payload!!

    override suspend fun getPayment(coin: String): PaymentResponse = api.getPayment(coin).payload!!

    override suspend fun getNetwork(coin: String): NetworkResponse = api.getNetwork(coin).payload!!

    override suspend fun getProfitDaily(coin: String): ProfitDailyResponse = api.getProfitDaily(coin).payload!!

    override suspend fun getCurrency(coin: String): CurrencyResponse = api.getCurrency(coin).payload!!
}