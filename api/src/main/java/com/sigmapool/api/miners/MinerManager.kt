package com.sigmapool.api.miners

import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.MinerDto

internal class MinerManager(private val minerService: IMinerService) : IMinerManager {

    override suspend fun getMiner(offset: Int, limit: Int): ManagerResult<List<MinerDto>> = try {
        ManagerResult(
            minerService
                .getMiners(offset, limit)
                .map {
                    MinerDto(
                        it.id,
                        it.name,
                        it.hashrate,
                        it.power,
                        it.btcValue,
                        it.revenueValue,
                        it.powerCost,
                        it.shutdownPrice
                    )
                }
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}