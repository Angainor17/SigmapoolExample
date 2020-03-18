package org.sigmapool.common.managers

import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.MinerDto

interface IMinerManager {
    suspend fun getMiner(page: Int, perPage: Int): ManagerResult<List<MinerDto>>
}