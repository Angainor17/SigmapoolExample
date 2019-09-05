package com.sigmapool.api.workers.models

class WorkerResponseItem(
    val title: String,
    val hashrate: Long,
    val hashrate24h: Long,
    val isOnline: Boolean
)