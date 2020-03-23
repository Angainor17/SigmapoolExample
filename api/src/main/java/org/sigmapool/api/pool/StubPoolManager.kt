package org.sigmapool.api.pool

import android.content.Context
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.models.*
import java.util.*

internal class StubPoolManager(private val context: Context) : IPoolManager {

    override suspend fun getCoin(coin: String): ManagerResult<CoinInfoDto> {
        return ManagerResult(
            CoinInfoDto(123123123123123, 12, arrayListOf("PPS", "PTS"), 11432f, 11000f)
        )
    }

    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> {
        return ManagerResult(
            PaymentDto(TimeIntervalDto(Date(Date().time - 3 * 60 * 60 * 1000), Date()), 0.01f)
        )
    }

    override suspend fun getCoins(): ManagerResult<ArrayList<CoinDto>> {
        return ManagerResult(
            arrayListOf(
                CoinDto(
                    "bsv",
                    "http://api.sigmapool.com/img/coins/bsv.png",
                    "TH/s",
                    0.00001488f,
                    167f,
                    155.154022f,
                    13356.2258f,
                    1184.9986f
                ),
                CoinDto(
                    "btc",
                    "http://api.sigmapool.com/img/coins/btc.png",
                    "TH/s",
                    0.00005092f,
                    167f,
                    155.154022f,
                    13356.2258f,
                    1184.9986f
                ),
                CoinDto(
                    "ltc",
                    "http://api.sigmapool.com/img/coins/ltc.png",
                    "MH/s",
                    0.00059338f,
                    167f,
                    155.154022f,
                    13356.2258f,
                    1184.9986f
                )
            )
        )
    }

    override suspend fun getNetwork(coin: String): ManagerResult<NetworkDto> {
        return ManagerResult(
            NetworkDto(
                1231f,
                12,
                7777777777777.32f,
                666666666666666f,
                555555555555555f,
                123,
                Date()
            )
        )
    }

    override suspend fun getProfitDaily(coin: String) = ManagerResult(ProfitDailyDto(17.1f))

    override suspend fun getCurrency(coin: String): ManagerResult<CurrencyDto> {
        return ManagerResult(
            CurrencyDto(
                10045.8883708f,
                657170.8749637865f,
                71453.36007732405f,
                9097.556508596479f
            )
        )
    }

    override suspend fun getScheme(coin: String) = ManagerResult((SchemeDto("PPS")))

    override suspend fun setScheme(coin: String, scheme: String) =
        ManagerResult((SchemeDto(scheme)))

    override suspend fun getThreshold(coin: String) = ManagerResult((ThresholdDto(0.02f)))

    override suspend fun setThreshold(coin: String, threshold: Float) =
        ManagerResult((ThresholdDto(0.02f)))
}