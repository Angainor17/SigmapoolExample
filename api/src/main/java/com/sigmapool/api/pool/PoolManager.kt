package com.sigmapool.api.pool

import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.*

internal class PoolManager(private val service: IPoolService) : IPoolManager {

    override suspend fun getCoin(coin: String): ManagerResult<CoinDto> = try {
        val coinResponse = service.getCoin(coin)
        ManagerResult(
            CoinDto(
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
        val response = service.getNetwork(coin)
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
        val response = service.getProfitDaily(coin)
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