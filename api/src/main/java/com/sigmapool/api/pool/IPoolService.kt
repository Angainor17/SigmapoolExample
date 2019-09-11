package com.sigmapool.api.pool

import com.sigmapool.api.pool.models.*

internal interface IPoolService {

    suspend fun getCoin(coin: String): CoinResponse
    suspend fun getPayment(coin: String): PaymentResponse
    suspend fun getNetwork(coin: String): NetworkResponse
    suspend fun getProfitDaily(coin: String): ProfitDailyResponse
    suspend fun getCurrency(coin: String): CurrencyResponse
}

