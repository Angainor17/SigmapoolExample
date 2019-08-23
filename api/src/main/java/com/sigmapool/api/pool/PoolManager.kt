package com.sigmapool.api.pool

import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PaymentDto
import com.sigmapool.common.models.TimeIntervalDto

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
}