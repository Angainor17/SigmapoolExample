package com.sigmapool.common.models

import java.util.*

class NetworkDto(
    val blockReward: Float,
    val blockTime: Int,
    val networkHashrate: Float,
    val networkDifficulty: Long,
    val blockHeight: Int,
    val nextDifficultyAt: Date
)