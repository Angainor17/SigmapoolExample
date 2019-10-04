package org.sigmapool.api.models

import org.sigmapool.api.miners.TitleModel

internal data class Miner(
    val id: Long,
    val title: TitleModel,
    val coin: String,
    val image: String,
    val hashrate: Long,
    val power: Int,
    val revenue: Float,
    val shutdownPrice: Float
)