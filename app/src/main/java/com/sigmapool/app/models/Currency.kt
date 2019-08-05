package com.sigmapool.app.models

import androidx.annotation.ArrayRes

class Currency(
    val scaleFrom: Int,
    val scaleTo: Int,
    val initValue: Int,
    @ArrayRes val stringArrayRes: Int,
    val step: Int // symbols after point
)