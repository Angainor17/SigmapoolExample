package com.sigmapool.api.earnings

import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.models.*
import java.util.*
import kotlin.collections.ArrayList

internal class EarningsManager(private val service: IEarningsService) : IEarningsManager {

    override suspend fun earningsDaily(coin: String): ManagerResult<EarningsDto> = try {
        val response = service.earningsDaily(coin)
        ManagerResult(EarningsDto(response.earnings))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun totalPaid(coin: String): ManagerResult<TotalPaidDto> = try {
        val response = service.totalPaid(coin)
        ManagerResult(TotalPaidDto(response.totalPaid))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun balance(coin: String): ManagerResult<BalanceDto> = try {
        val response = service.balance(coin)
        ManagerResult(BalanceDto(response.balance))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun payments(
        coin: String,
        page: Int
    ): ManagerResult<ArrayList<PaymentItemDto>> = try {
        val response = service.payments(coin, page)

        ManagerResult(ArrayList(response.map {
            PaymentItemDto(
                Date(it.date ?: Date().time),
                it.amount ?: 0f,
                it.bonus,
                it.diff ?: 0L,
                it.transaction,
                it.type
            )
        }))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getLastPayment(coin: String): ManagerResult<LastPaymentDto> = try {
        ManagerResult(LastPaymentDto(service.getLastPayment(coin).date))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getEstimatedProfit(coin: String): ManagerResult<EstimatedProfitDto> = try {
        ManagerResult(EstimatedProfitDto(service.getEstimatedProfit(coin).estimatedProfit))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getAddress(coin: String): ManagerResult<AddressDto> = try {
        ManagerResult(AddressDto(service.address(coin).address ?: ""))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}