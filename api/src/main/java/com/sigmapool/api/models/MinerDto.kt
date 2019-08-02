package com.sigmapool.api.models

internal data class MinerDto(
    val id: Long,
    val name: String,
    val hashrate: Int,
    val power: Int,
    val btcValue: Int,
    val revenueValue: Float,
    val powerCost: Float,
    val shutdownPrice: Float,
    val profit: Float

)