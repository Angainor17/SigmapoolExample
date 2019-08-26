package com.sigmapool.api.pool

import com.sigmapool.api.pool.models.CoinResponse
import com.sigmapool.api.pool.models.NetworkResponse
import com.sigmapool.api.pool.models.PaymentResponse
import com.sigmapool.api.pool.models.ProfitDailyResponse

internal interface IPoolService {

    suspend fun getCoin(coin: String): CoinResponse
    suspend fun getPayment(coin: String): PaymentResponse
    suspend fun getNetwork(coin: String): NetworkResponse
    suspend fun getProfitDaily(coin: String): ProfitDailyResponse

}

