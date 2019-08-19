package com.sigmapool.common.models

data class MinerDto(
    val id: Long,
    val title: String,
    val hashrate: Int,
    val power: Int,
    val btcValue: Int,
    val revenueValue: Float,
    val shutdownPrice: Float

)