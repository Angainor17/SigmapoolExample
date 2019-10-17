package org.sigmapool.api.pool

import org.sigmapool.api.pool.models.*

internal interface IPoolService {

    suspend fun getCoin(coin: String): CoinResponse
    suspend fun getPayment(coin: String): PaymentResponse
    suspend fun getNetwork(coin: String): NetworkResponse
    suspend fun getProfitDaily(coin: String): ProfitDailyResponse
    suspend fun getCurrency(coin: String): CurrencyResponse

    suspend fun getCoins(): ArrayList<CoinModelResponse>

    suspend fun getScheme(coin: String): SchemeResponse
    suspend fun setScheme(coin: String, scheme: String): SchemeResponse

    suspend fun getThreshold(coin: String): ThresholdResponse
    suspend fun setThreshold(coin: String, threshold: Float): ThresholdResponse

}

