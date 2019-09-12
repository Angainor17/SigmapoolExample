package com.sigmapool.api.earnings.models

internal class PaymentListResponse(
    val payments: ArrayList<PaymentItemResponse>,
    val earningsCount: Int
)