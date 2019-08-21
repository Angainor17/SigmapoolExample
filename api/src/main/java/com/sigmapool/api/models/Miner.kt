package com.sigmapool.api.models

internal data class Miner(
    val id: Long,
    val title: String,
    val coin: String,
    val hashrate: Long,
    val power: Int,
    val revenue: Float,
    val shutdownPrice: Float
)