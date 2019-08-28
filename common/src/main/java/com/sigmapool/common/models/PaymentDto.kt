package com.sigmapool.common.models

data class PaymentDto(
    val time: PaymentTime,
    val min: Float
)

data class PaymentTime(
    val from: String,
    val to: String
)
