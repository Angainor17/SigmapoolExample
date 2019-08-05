package com.sigmapool.common

import com.sigmapool.common.models.MinerDto

interface IMinerManager {
    suspend fun getMiner(offset: Int, limit: Int): ManagerResult<List<MinerDto>>
}