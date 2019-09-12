package com.sigmapool.api.pool

import android.content.Context
import com.sigmapool.api.R
import com.sigmapool.api.hasConnection
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.*
import kotlinx.coroutines.delay
import java.util.*

internal class StubPoolManager(private val context: Context) : IPoolManager {

    override suspend fun getCoin(coin: String): ManagerResult<CoinDto> {
        delay(10000)

        if (!hasConnection(context)) return ManagerResult(error = context.getString(R.string.no_connection))

        return ManagerResult(
            CoinDto(123123123123123, 12, arrayListOf("PPS", "PTS"), 11432f, 11000f)
        )
    }

    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> {
        delay(10000)

        if (!hasConnection(context)) return ManagerResult(error = context.getString(R.string.no_connection))

        return ManagerResult(
            PaymentDto(TimeIntervalDto(Date(Date().time - 3 * 60 * 60 * 1000), Date()), 0.01f)
        )
    }

    override suspend fun getNetwork(coin: String): ManagerResult<NetworkDto> {
        delay(5000)

        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }

        return ManagerResult(
            NetworkDto(
                1231f,
                12,
                7777777777777.32f,
                666666666666666L,
                555555555555555L,
                123,
                Date()
            )
        )
    }

    override suspend fun getProfitDaily(coin: String): ManagerResult<ProfitDailyDto> {
        delay(5000)

        if (!hasConnection(context)) return ManagerResult(error = context.getString(R.string.no_connection))

        return ManagerResult(
            ProfitDailyDto(17.1f)
        )
    }

    override suspend fun getCurrency(coin: String): ManagerResult<CurrencyDto> {
        delay(5000)

        if (!hasConnection(context)) return ManagerResult(error = context.getString(R.string.no_connection))

        return ManagerResult(
            CurrencyDto(
                10045.8883708f,
                657170.8749637865f,
                71453.36007732405f,
                9097.556508596479f
            )
        )
    }

    override suspend fun getScheme(coin: String): ManagerResult<SchemeDto> {
        delay(1500)

        if (!hasConnection(context)) return ManagerResult(error = context.getString(R.string.no_connection))

        return ManagerResult((SchemeDto("PPS")))
    }

    override suspend fun setScheme(coin: String, scheme: String): ManagerResult<SchemeDto> {
        delay(1500)

        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }
        return ManagerResult((SchemeDto(scheme)))
    }

    override suspend fun getThreshold(coin: String): ManagerResult<ThresholdDto> {
        delay(1500)

        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }
        return ManagerResult((ThresholdDto(0.02f)))
    }

    override suspend fun setThreshold(coin: String, threshold: Float): ManagerResult<ThresholdDto> {
        delay(1500)

        if (!hasConnection(context)) {
            return ManagerResult(error = context.getString(R.string.no_connection))
        }
        return ManagerResult((ThresholdDto(0.02f)))
    }
}