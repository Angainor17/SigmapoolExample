package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner


internal interface IMinerService {
    suspend fun getMiners(offset: Int, limit: Int): List<Miner>
}

