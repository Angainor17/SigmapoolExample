package com.sigmapool.api.earnings

import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.models.*

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


    override suspend fun payments(coin: String): ManagerResult<ArrayList<PaymentItemDto>> = try {
        val response = service.payments(coin)

        ManagerResult(ArrayList(response.map {
            PaymentItemDto(
                it.date,
                it.amount,
                it.bonus,
                it.diff
            )
        }))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}