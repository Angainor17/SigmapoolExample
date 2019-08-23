package com.sigmapool.api.coin

internal class CoinResponse(
    val poolHashrate: Long,
    val poolWorkers: Int,
    val payoutScheme: ArrayList<String>,
    val price: Float,
    val previousPrice: Float
)