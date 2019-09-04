package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner
import com.sigmapool.api.providers.IApiServiceProvider
import kotlinx.coroutines.delay
import kotlin.math.max
import kotlin.random.Random

internal class StubMinerService(apiProvider: IApiServiceProvider) : IMinerService {

    private val items = List(120) {
        Miner(
            it.toLong(),
            TitleModel("Antminer S$it Pro", "", ""),
            "btc",
            10000000000000,
            2500 + it,
            20f + it + Random.nextInt(0, 10) + (Random.nextInt(0, 10) / 100),
            15f + (it / 100)
        )
    }

    override suspend fun getMiners(page: Int, perPage: Int): List<Miner> {
        if (page >= items.size) {
            return emptyList()
        }

        delay(5000)

        if (perPage >= items.size) {
            return items
        }

        var start = max((page - 1), 0) * perPage
        if (start != 0) start++

        return items.slice(IntRange(start, start + perPage))
    }
}