package com.sigmapool.common.models

data class WorkerDto(
    val title: String,
    val hashrate: Long,
    val hashrate24h: Long,
    val isOnline: Boolean
)