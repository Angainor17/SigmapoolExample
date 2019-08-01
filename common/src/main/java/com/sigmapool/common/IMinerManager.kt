package com.sigmapool.common

import com.sigmapool.common.models.Miner

interface IMinerManager {
    suspend fun getMiner(offset: Int, limit: Int): ManagerResult<List<Miner>>
}