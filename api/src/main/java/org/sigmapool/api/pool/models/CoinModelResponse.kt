package org.sigmapool.api.pool.models

class CoinModelResponse(
    val code: String,
    val icon: String,
    val unit: String,
    val priceUsd: Float,
    val priceEur: Float,
    val priceRub: Float,
    val priceCny: Float
)