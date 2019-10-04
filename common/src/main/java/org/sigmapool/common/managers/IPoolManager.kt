package org.sigmapool.common.managers

import org.sigmapool.common.models.*

interface IPoolManager {

    suspend fun getCoin(coin: String): ManagerResult<CoinDto>

    suspend fun getPayment(coin: String): ManagerResult<PaymentDto>

    suspend fun getNetwork(coin: String): ManagerResult<NetworkDto>

    suspend fun getProfitDaily(coin: String): ManagerResult<ProfitDailyDto>

    suspend fun getCurrency(coin: String): ManagerResult<CurrencyDto>

    suspend fun getScheme(coin: String): ManagerResult<SchemeDto>

    suspend fun setScheme(coin: String, scheme: String): ManagerResult<SchemeDto>

    suspend fun getThreshold(coin: String): ManagerResult<ThresholdDto>

    suspend fun setThreshold(coin: String, threshold: Float): ManagerResult<ThresholdDto>

}