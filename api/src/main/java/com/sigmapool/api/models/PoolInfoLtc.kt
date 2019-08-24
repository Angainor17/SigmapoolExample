package com.sigmapool.api.models

data class PoolInfoLtc(
    val fee: LtcFee,
    val settlementTime: String,
    val addressChangeTimeout: Int,
    val stratumURLs: Array<String>
)

data class LtcFee(
    val pps: Float
)