package org.sigmapool.api.miners

import org.sigmapool.api.BASE_URL
import org.sigmapool.common.managers.IMinerManager
import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.MinerDto

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
                        "https://$BASE_URL" + it.image,
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