package com.sigmapool.api.api

import com.sigmapool.api.models.Miner


internal interface IMinerService {
    suspend fun getMiners(offset: Int, limit: Int): List<Miner>
}

