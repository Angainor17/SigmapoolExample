package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner
import kotlinx.coroutines.delay

internal class StubMinerService : IMinerService {

    private val items = List(120) {
        Miner(
            it.toLong(),
            "Antminer S$it Pro",
            56 + (it / 10),
            2780 + it,
            11000 + it,
            20.24f + (it / 10),
            2.01f + (it / 100),
            18.23f + (it / 100),
            18.24f + (it / 100)
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