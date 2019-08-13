package com.sigmapool.common.models

data class PoolInfoDto(
    val feePps: Float,
    val feeFpps: Float,
    val settlementTime: String,
    val addressChangeTimeout: Int,
    val stratumURLs: String
)