package org.sigmapool.common.managers

import org.sigmapool.common.models.*


interface IPoolInfoManager {

    suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto>

    suspend fun getDailyProfit(coin: String): ManagerResult<DailyProfitDto>

    suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto>

    suspend fun getPayment(coin: String): ManagerResult<PaymentDto>

    suspend fun getSettlementDetails(
        coin: String,
        lang: String
    ): ManagerResult<SettlementDetailsDto>
}