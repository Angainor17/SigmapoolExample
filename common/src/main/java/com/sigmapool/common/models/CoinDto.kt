package com.sigmapool.common.models

class CoinDto(
    val poolHashrate: Long,
    val poolWorkers: Int,
    val payoutScheme: ArrayList<String>,
    val price: Float,
    val previousPrice: Float
)