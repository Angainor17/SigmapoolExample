package org.sigmapool.api.earnings

import org.sigmapool.api.earnings.models.*

internal interface IEarningsService {

    suspend fun earningsDaily(coin: String): EarningsResponse

    suspend fun totalPaid(coin: String): TotalPaidResponse

    suspend fun balance(coin: String): BalanceResponse

    suspend fun payments(coin: String, page: Int): ArrayList<PaymentItemResponse>

    suspend fun getLastPayment(coin: String): LastPaymentResponse

    suspend fun getEstimatedProfit(coin: String): EstimatedProfitResponse

    suspend fun address(coin: String): AddressResponse
}

