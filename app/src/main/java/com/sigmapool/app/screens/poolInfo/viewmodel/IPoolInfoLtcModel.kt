package com.sigmapool.app.screens.poolInfo.viewmodel

import com.sigmapool.common.models.*

interface IPoolInfoLtcModel {
    suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto>
    suspend fun getDailyProfit(coin:String): ManagerResult<DailyProfitDto>
    suspend fun getPayment(coin:String): ManagerResult<PaymentDto>
    suspend fun getSettlementDetails(coin:String): ManagerResult<SettlementDetailsDto> // TODO: lang param
}
