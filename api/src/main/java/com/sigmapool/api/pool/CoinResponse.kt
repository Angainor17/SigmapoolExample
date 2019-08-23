package com.sigmapool.api.pool

internal class CoinResponse(
    val poolHashrate: Long,
    val poolWorkers: Int,
    val payoutScheme: ArrayList<String>,
    val price: Float,
    val previousPrice: Float
)