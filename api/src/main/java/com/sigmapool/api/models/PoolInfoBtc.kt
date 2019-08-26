package com.sigmapool.api.models

data class PoolInfoBtc(
    val fee: BtcFee,
    val settlementTime: String,
    val addressChangeTimeout: Int,
    val stratumURLs: Array<String>

)

data class BtcFee(
    val pps: Float,
    val fpps: Float
)

//TODO: вынести в отдельный файл
data class DailyProfitBtc(
    val profit: Float
)