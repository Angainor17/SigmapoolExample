package com.sigmapool.api.pool

import android.content.Context
import com.sigmapool.api.R
import com.sigmapool.api.hasConnection
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.*
import java.util.*

internal class StubPoolManager(private val context: Context) : IPoolManager {

    override suspend fun getCoin(coin: String): ManagerResult<CoinDto> {
        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }

        return ManagerResult(
            CoinDto(123123123123123, 12, arrayListOf("PPS", "PTS"), 11432f, 11000f)
        )
    }

    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> {
        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }

        return ManagerResult(
            PaymentDto(TimeIntervalDto(Date(Date().time - 3 * 60 * 60 * 1000), Date()), 0.01f)
        )
    }

    override suspend fun getNetwork(coin: String): ManagerResult<NetworkDto> {
        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }

        return ManagerResult(
            NetworkDto(
                1231f,
                12,
                7777777777777.32f,
                666666666666666L,
                123,
                Date()
            )
        )
    }

    override suspend fun getProfitDaily(coin: String): ManagerResult<ProfitDailyDto> {
        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }

        return ManagerResult(
            ProfitDailyDto(17.10f)
        )
    }
}