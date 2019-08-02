package com.sigmapool.common.models

data class Miner(
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