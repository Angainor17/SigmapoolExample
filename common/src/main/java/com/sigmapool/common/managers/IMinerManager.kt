package com.sigmapool.common.managers

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.MinerDto

interface IMinerManager {
    suspend fun getMiner(page: Int, perPage: Int): ManagerResult<List<MinerDto>>
}