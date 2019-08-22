package com.sigmapool.common.models

data class PoolInfoBtcDto(
    val feePps: Float,
    val feeFpps: Float,
    val settlementTime: String,
    val addressChangeTimeout: Int,
    val stratumURLs: Array<String>
)