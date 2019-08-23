package com.sigmapool.api.pool

import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.*

internal class PoolManager(private val service: IPoolService) : IPoolManager {

    override suspend fun getCoin(): ManagerResult<CoinDto> = try {
        val coinResponse = service.getCoin()
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

    override suspend fun getPayment(): ManagerResult<PaymentDto> = try {
        val response = service.getPayment()
        ManagerResult(
            PaymentDto(
                TimeIntervalDto(response.time.from, response.time.from),
                response.min
            )
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun getNetwork(): ManagerResult<NetworkDto> = try {
        val response = service.getNetwork()
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

    override suspend fun getProfitDaily(): ManagerResult<ProfitDailyDto> = try {
        val response = service.getProfitDaily()
        ManagerResult(ProfitDailyDto(response.profit))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}