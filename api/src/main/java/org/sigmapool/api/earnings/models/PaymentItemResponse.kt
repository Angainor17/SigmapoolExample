package org.sigmapool.api.earnings.models

internal class PaymentItemResponse(
    val date: Long?,
    val amount: Float?,
    val bonus: Int?,
    val scheme: String?,
    val diff: Long?,
    val transaction: String?,
    val type: String?
)