package org.sigmapool.common.models

class CoinInfoDto(
    val poolHashrate: Long,
    val poolWorkers: Int,
    val payoutScheme: ArrayList<String>,
    val price: Float,
    val previousPrice: Float
)