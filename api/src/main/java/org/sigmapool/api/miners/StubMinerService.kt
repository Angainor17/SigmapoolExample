package org.sigmapool.api.miners

import android.content.Context
import org.sigmapool.api.models.Miner
import kotlin.math.max
import kotlin.random.Random

internal class StubMinerService(val context: Context) : IMinerService {

    private val items = List(50) {
        Miner(
            it.toLong(),
            TitleModel("Antminer S$it Pro", "", ""),
            "btc",
            "/uploads/miner/2e2be0a091fa05f8becc3f9c526ce3df.jpg",
            10000000000000,
            2500 + it,
            20f + it + Random.nextInt(0, 10) + (Random.nextInt(0, 10) / 100),
            15f + (it / 100)
        )
    }

    override suspend fun getMiners(page: Int, perPage: Int): List<Miner> {
        try {
            if (perPage >= items.size) return items

            var start = max((page - 1), 0) * perPage
            if (start != 0) start++

            if (start > items.size) return emptyList()

            return items.slice(IntRange(start, start + perPage))
        } catch (e: Exception) {
            return emptyList()
        }
    }
}