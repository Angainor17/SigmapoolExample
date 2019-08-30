package com.sigmapool.common.models

data class PoolInfoLtcDto(
    val feePps: Float,
    val settlementTime: String?,
    val addressChangeTimeout: Int,
    val stratumURLs: Array<String>
)