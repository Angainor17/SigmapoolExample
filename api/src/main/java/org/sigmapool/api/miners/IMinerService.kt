package org.sigmapool.api.miners

import org.sigmapool.api.models.Miner


internal interface IMinerService {
    suspend fun getMiners(page: Int, perPage: Int): List<Miner>
}

