package com.sigmapool.common.models

data class MinerDto(
    val id: Long,
    val title: String,
    val coin: String,
    val hashrate: Long,
    val power: Int,
    val revenue: Float,
    val shutdownPrice: Float
)