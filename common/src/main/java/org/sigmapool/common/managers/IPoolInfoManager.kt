package org.sigmapool.common.managers

import org.sigmapool.common.models.*


interface IPoolInfoManager {

    suspend fun getPoolInfo(coin: String): ManagerResult<PoolInfoCoinDto>

    suspend fun getDailyProfit(coin: String): ManagerResult<DailyProfitDto>

    suspend fun getPayment(coin: String): ManagerResult<PaymentDto>

    suspend fun getSettlementDetails(
        coin: String,
        lang: String
    ): ManagerResult<SettlementDetailsDto>
}