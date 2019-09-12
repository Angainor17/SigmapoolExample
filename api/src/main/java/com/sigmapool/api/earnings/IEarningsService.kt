package com.sigmapool.api.earnings

import com.sigmapool.api.earnings.models.BalanceResponse
import com.sigmapool.api.earnings.models.EarningsResponse
import com.sigmapool.api.earnings.models.PaymentItemResponse
import com.sigmapool.api.earnings.models.TotalPaidResponse

internal interface IEarningsService {

    suspend fun earningsDaily(coin: String): EarningsResponse

    suspend fun totalPaid(coin: String): TotalPaidResponse

    suspend fun balance(coin: String): BalanceResponse

    suspend fun payments(coin: String, page: Int): ArrayList<PaymentItemResponse>
}

