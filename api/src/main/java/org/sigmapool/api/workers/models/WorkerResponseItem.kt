package org.sigmapool.api.workers.models

class WorkerResponseItem(
    val title: String,
    val hashrate: Float,
    val hashrate24h: Float,
    val isOnline: Boolean
)