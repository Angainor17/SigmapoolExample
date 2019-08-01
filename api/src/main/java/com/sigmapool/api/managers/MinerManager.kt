package com.sigmapool.api.managers

import com.sigmapool.api.api.IMinerService
import com.sigmapool.common.IMinerManager
import com.sigmapool.common.ManagerResult
import com.sigmapool.common.models.Miner
import org.kodein.di.generic.instance

class MinerManager : IMinerManager {

    private val postsService: IMinerService by managerKodein.instance()

    override suspend fun getMiner(offset: Int, limit: Int): ManagerResult<List<Miner>> {
        return try {
            ManagerResult(
                postsService
                    .getMiners(offset, limit)
                    .map {
                        Miner(
                            it.id,
                            it.title,
                            it.text
                        )
                    }
            )
        } catch (e: Throwable) {
            ManagerResult(error = e.message)
        }
    }
}