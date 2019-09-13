package com.sigmapool.api.earnings

import com.sigmapool.api.earnings.models.*

internal interface IEarningsService {

    suspend fun earningsDaily(coin: String): EarningsResponse

    suspend fun totalPaid(coin: String): TotalPaidResponse

    suspend fun balance(coin: String): BalanceResponse

    suspend fun payments(coin: String, page: Int): ArrayList<PaymentItemResponse>

    suspend fun getLastPayment(coin: String): LastPaymentResponse
}

