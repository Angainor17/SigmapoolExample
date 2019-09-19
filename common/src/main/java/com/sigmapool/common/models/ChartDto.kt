package com.sigmapool.common.models

data class ChartDto(
    val series: Array<SeriesDto>
)

data class SeriesDto(
    val time: String,
    val hashrate: Long
)