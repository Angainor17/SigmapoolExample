package com.sigmapool.common.managers

import com.sigmapool.common.models.*


interface IPoolInfoManager {
    suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto>
    suspend fun getDailyProfit(coin:String): ManagerResult<DailyProfitDto>
    suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto>
    suspend fun getPayment(coin:String): ManagerResult<PaymentDto>
    suspend fun getSettlementDetails(coin:String): ManagerResult<SettlementDetailsDto>
}