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
}