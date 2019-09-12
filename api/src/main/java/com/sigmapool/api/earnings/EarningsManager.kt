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
                it.amount,
                it.bonus,
                it.diff,
                it.transaction,
                it.type
            )
        }))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}