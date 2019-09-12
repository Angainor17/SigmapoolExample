package com.sigmapool.api.earnings.models

import java.util.*

internal class PaymentItemResponse(
    val date: Date,
    val amount: Float,
    val bonus: Int,
    val diff: Long
)