package com.sigmapool.common.models

import androidx.annotation.ArrayRes

/**
 * TODO
 */
class Currency(
    val scaleFrom: Int,
    val scaleTo: Int,
    val initValue: Int,
    @ArrayRes val stringArrayRes: Int,
    val step: Int// number after point
)