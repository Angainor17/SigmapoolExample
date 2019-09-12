package com.sigmapool.common.managers

import com.sigmapool.common.models.*

interface IPoolManager {

    suspend fun getCoin(coin: String): ManagerResult<CoinDto>

    suspend fun getPayment(coin: String): ManagerResult<PaymentDto>

    suspend fun getNetwork(coin: String): ManagerResult<NetworkDto>

    suspend fun getProfitDaily(coin: String): ManagerResult<ProfitDailyDto>

    suspend fun getCurrency(coin: String): ManagerResult<CurrencyDto>

    suspend fun getScheme(coin: String): ManagerResult<SchemeDto>

    suspend fun setScheme(coin: String, scheme: String): ManagerResult<SchemeDto>


}