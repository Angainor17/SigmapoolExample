package com.sigmapool.api.miners

import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.MinerDto

internal class MinerManager(private val minerService: IMinerService) : IMinerManager {

    override suspend fun getMiner(page: Int, perPage: Int): ManagerResult<List<MinerDto>> = try {
        ManagerResult(
            minerService
                .getMiners(page, perPage)
                .map {
                    MinerDto(
                        it.id,
                        it.title.en,
                        it.coin,
                        it.hashrate,
                        it.power,
                        it.revenue,
                        it.shutdownPrice
                    )
                }
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}