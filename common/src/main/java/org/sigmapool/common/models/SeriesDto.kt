package org.sigmapool.common.models

import java.util.*

data class SeriesDto(
    val time: Date,
    val hashrate: Float = 0f,
    val shares: Float = 0f
)