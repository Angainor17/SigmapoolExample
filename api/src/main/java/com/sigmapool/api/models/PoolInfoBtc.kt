package com.sigmapool.api.models

data class PoolInfoBtc(
    val feePps: Float,
    val feeFpps: Float,
    val settlementTime: String,
    val addressChangeTimeout: Int,
    val stratumURLs: Array<String>
)