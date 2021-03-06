package org.sigmapool.common.models

class CoinDto(
    val code: String,
    val icon: String,
    val unit: String,
    val profitability: Float,
    val priceUsd: Float,
    val priceEur: Float,
    val priceRub: Float,
    val priceCny: Float
)