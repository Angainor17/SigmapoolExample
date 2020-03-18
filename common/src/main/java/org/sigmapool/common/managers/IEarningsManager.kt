package org.sigmapool.common.managers

import org.sigmapool.common.models.*

interface IEarningsManager {

    suspend fun earningsDaily(coin: String): ManagerResult<EarningsDto>

    suspend fun totalPaid(coin: String): ManagerResult<TotalPaidDto>

    suspend fun balance(coin: String): ManagerResult<BalanceDto>

    suspend fun payments(coin: String, page: Int): ManagerResult<ArrayList<PaymentItemDto>>

    suspend fun getLastPayment(coin: String): ManagerResult<LastPaymentDto>

    suspend fun getEstimatedProfit(coin: String): ManagerResult<EstimatedProfitDto>

    suspend fun getAddress(coin: String): ManagerResult<AddressDto>
}