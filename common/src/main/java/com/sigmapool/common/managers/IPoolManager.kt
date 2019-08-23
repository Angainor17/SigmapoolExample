package com.sigmapool.common.managers

import com.sigmapool.common.models.*

interface IPoolManager {

    suspend fun getCoin(): ManagerResult<CoinDto>

    suspend fun getPayment(): ManagerResult<PaymentDto>

    suspend fun getNetwork(): ManagerResult<NetworkDto>

    suspend fun getProfitDaily(): ManagerResult<ProfitDailyDto>
}