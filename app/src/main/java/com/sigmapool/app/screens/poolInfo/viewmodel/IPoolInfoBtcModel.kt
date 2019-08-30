package com.sigmapool.app.screens.poolInfo.viewmodel

import com.sigmapool.common.models.*

interface IPoolInfoBtcModel {
    suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto>
    suspend fun getDailyProfit(coin:String): ManagerResult<DailyProfitDto>
    suspend fun getPayment(coin:String): ManagerResult<PaymentDto>
    suspend fun getSettlementDetails(coin:String): ManagerResult<SettlementDetailsDto> // TODO: lang param
}
