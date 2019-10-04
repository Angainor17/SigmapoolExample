package org.sigmapool.sigmapool.screens.poolInfo.model

import org.sigmapool.common.models.*

interface IPoolInfoBtcModel {
    suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto>
    suspend fun getDailyProfit(coin: String): ManagerResult<DailyProfitDto>
    suspend fun getPayment(coin: String): ManagerResult<PaymentDto>
    suspend fun getSettlementDetails(coin: String): ManagerResult<SettlementDetailsDto>
}
