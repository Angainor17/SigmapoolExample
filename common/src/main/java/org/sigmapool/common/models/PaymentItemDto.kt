package org.sigmapool.common.models

import java.util.*

class PaymentItemDto(

    val date: Date,
    val amount: Float,
    val bonus: Int?,
    val scheme: String?,
    val diff: Long,
    val transaction: String?,
    val type: String?

)