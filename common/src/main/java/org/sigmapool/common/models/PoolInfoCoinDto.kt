package org.sigmapool.common.models

data class PoolInfoCoinDto(
    val feePps: Float?,
    val feeFpps: Float?,
    val feeSolo: Float?,
    val feePplns: Float?,
    val settlementTime: String?,
    val addressChangeTimeout: Int,
    val stratumURLs: Array<String>
)

