package org.sigmapool.common.models

import java.util.*

class NetworkDto(
    val blockReward: Float,
    val blockTime: Int,
    val networkHashrate: Float,
    val networkDifficulty: Float,
    val nextDifficulty: Float,
    val blockHeight: Int,
    val nextDifficultyAt: Date
)