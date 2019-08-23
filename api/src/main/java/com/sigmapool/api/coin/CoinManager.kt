package com.sigmapool.api.coin

import com.sigmapool.common.managers.ICoinManager
import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.ManagerResult

internal class CoinManager(private val service: ICoinService) : ICoinManager {

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
}