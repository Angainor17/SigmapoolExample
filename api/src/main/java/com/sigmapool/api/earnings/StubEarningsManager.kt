package com.sigmapool.api.earnings

import android.content.Context
import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.models.*
import java.util.*
import kotlin.collections.ArrayList

internal class StubEarningsManager(private val context: Context) : IEarningsManager {

    override suspend fun earningsDaily(coin: String): ManagerResult<EarningsDto> {
        return ManagerResult(EarningsDto(42f))
    }

    override suspend fun totalPaid(coin: String): ManagerResult<TotalPaidDto> {
        return ManagerResult(TotalPaidDto(43342f))
    }

    override suspend fun balance(coin: String): ManagerResult<BalanceDto> {
        return ManagerResult(BalanceDto(123123f))
    }

    override suspend fun payments(
        coin: String,
        page: Int
    ): ManagerResult<ArrayList<PaymentItemDto>> {
        return ManagerResult(
            ArrayList(List(15) {
                PaymentItemDto(
                    Date(Date().time - it * 2L * 24 * 60 * 60 * 1000),
                    100.123123f + it,
                    it,
                    "FPPS",
                    1000000000L * it,
                    null,
                    null
                )
            })
        )
    }

    override suspend fun getLastPayment(coin: String): ManagerResult<LastPaymentDto> {
        return ManagerResult((LastPaymentDto(Date(Date().time - 5L * 24 * 60 * 60 * 1000))))
    }

    override suspend fun getEstimatedProfit(coin: String): ManagerResult<EstimatedProfitDto> {
        return ManagerResult(EstimatedProfitDto(1710f))
    }

    override suspend fun getAddress(coin: String): ManagerResult<AddressDto> {
        return ManagerResult(AddressDto("1LK1kzAvNuoiqkyqr3G8v55krE71ZpBEU"))
    }
}