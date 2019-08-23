package com.sigmapool.api.pool.models

import java.util.*

class NetworkResponse(
    val blockReward: Float,
    val blockTime: Int,
    val networkHashrate: Float,
    val networkDifficulty: Long,
    val blockHeight: Int,
    val nextDifficultyAt: Date
)