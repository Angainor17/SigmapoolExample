package org.sigmapool.api.pool

import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.models.*

internal class PoolManager(private val service: IPoolService) : IPoolManager {

    override suspend fun getCoin(coin: String): ManagerResult<CoinInfoDto> = try {
        val coinResponse = service.getCoin(coin.toLowerCase())
        ManagerResult(
            CoinInfoDto(
                coinResponse.poolHashrate,
                coinResponse.poolWorkers,
                coinResponse.payoutScheme,
                coinResponse.price,
                coinResponse.previousPrice
            )
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getCoins(): ManagerResult<ArrayList<CoinDto>> = try {
        val response = service.getCoins()
        ManagerResult(
            ArrayList(response.map {
                CoinDto(
                    it.code,
                    it.icon,
                    it.unit,
                    it.profitability,
                    it.priceUsd,
                    it.priceEur,
                    it.priceRub,
                    it.priceCny
                )
            })
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }


    override suspend fun getPayment(coin: String): ManagerResult<PaymentDto> = try {
        val response = service.getPayment(coin)
        ManagerResult(
            PaymentDto(
                TimeIntervalDto(response.time.from, response.time.from),
                response.min
            )
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getNetwork(coin: String): ManagerResult<NetworkDto> = try {
        val response = service.getNetwork(coin.toLowerCase())
        ManagerResult(
            NetworkDto(
                response.blockReward,
                response.blockTime,
                response.networkHashrate,
                response.networkDifficulty,
                response.nextDifficulty,
                response.blockHeight,
                response.nextDifficultyAt
            )
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getProfitDaily(coin: String): ManagerResult<ProfitDailyDto> = try {
        val response = service.getProfitDaily(coin.toLowerCase())
        ManagerResult(ProfitDailyDto(response.profit))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getCurrency(coin: String): ManagerResult<CurrencyDto> = try {
        val response = service.getCurrency(coin)
        ManagerResult(
            CurrencyDto(
                response.usd,
                response.rub,
                response.cny,
                response.eur
            )
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getScheme(coin: String): ManagerResult<SchemeDto> = try {
        ManagerResult(SchemeDto(service.getScheme(coin).scheme))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun setScheme(coin: String, scheme: String): ManagerResult<SchemeDto> = try {
        ManagerResult(SchemeDto(service.setScheme(coin, scheme).scheme))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getThreshold(coin: String) = try {
        ManagerResult(ThresholdDto(service.getThreshold(coin).threshold))
    } catch (e: Throwable) {
        ManagerResult<ThresholdDto>(error = e.message)
    }

    override suspend fun setThreshold(coin: String, threshold: Float) = try {
        ManagerResult(ThresholdDto(service.setThreshold(coin, threshold).threshold))
    } catch (e: Throwable) {
        ManagerResult<ThresholdDto>(error = e.message)
    }
}