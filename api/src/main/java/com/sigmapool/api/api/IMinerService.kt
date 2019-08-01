package com.sigmapool.api.api

import com.sigmapool.api.models.MinerDto

internal interface IMinerService {
    suspend fun getMiners(offset: Int, limit: Int): List<MinerDto>
}

