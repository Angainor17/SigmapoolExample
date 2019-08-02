package com.sigmapool.api.api

import com.sigmapool.api.models.MinerDto
import kotlinx.coroutines.delay

internal class StubMinerService : IMinerService {

    private val items = List(200) {
        MinerDto(
            it.toLong(),
            "Antminer S$it Pro",
            56,
            2780,
            11000,
            20.24f,
            2.01f,
            18.23f,
            18.24f
        )
    }

    override suspend fun getMiners(offset: Int, limit: Int): List<MinerDto> {
        if (offset >= items.size) {
            return emptyList()
        }

        delay(2000)

        return items.slice(IntRange(offset, offset + limit - 1))
    }
}