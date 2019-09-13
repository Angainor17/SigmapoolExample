package com.sigmapool.common.managers

import com.sigmapool.common.models.*

interface IEarningsManager {

    suspend fun earningsDaily(coin: String): ManagerResult<EarningsDto>

    suspend fun totalPaid(coin: String): ManagerResult<TotalPaidDto>

    suspend fun balance(coin: String): ManagerResult<BalanceDto>

    suspend fun payments(coin: String, page: Int): ManagerResult<ArrayList<PaymentItemDto>>

    suspend fun getLastPayment(coin: String): ManagerResult<LastPaymentDto>
}