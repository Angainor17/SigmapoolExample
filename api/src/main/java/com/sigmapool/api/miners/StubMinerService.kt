package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner
import kotlinx.coroutines.delay

internal class StubMinerService : IMinerService {

    private val items = List(120) {
        Miner(
            it.toLong(),
            "Antminer S$it Pro",
            "btc",
            10000000000000,
            2500 + it,
            20f + (it / 10),
            15f + (it / 100)
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