package org.sigmapool.api.models

data class CoinInfo(
    val fee: CoinFee,
    val settlementTime: String,
    val addressChangeTimeout: Int,
    val stratumURLs: Array<String>
)

data class CoinFee(
    val pps: Float?,
    val pplns: Float?,
    val fpps: Float?,
    val solo: Float?
)

//TODO: вынести в отдельный файл
data class DailyProfit(
    val profit: Float
)