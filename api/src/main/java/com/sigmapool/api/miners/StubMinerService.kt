package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner
import kotlinx.coroutines.delay

internal class StubMinerService : IMinerService {

    private val items = List(200) {
        Miner(
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

    override suspend fun getMiners(offset: Int, limit: Int): List<Miner> {
        if (offset >= items.size) {
            return emptyList()
        }

        delay(2000)

        return items.slice(IntRange(offset, offset + limit - 1))
    }
}