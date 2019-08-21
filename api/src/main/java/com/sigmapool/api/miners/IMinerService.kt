package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner


internal interface IMinerService {
    suspend fun getMiners(page: Int, perPage: Int): List<Miner>
}

