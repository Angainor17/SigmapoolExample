package org.sigmapool.sigmapool.provider.currency.models

import androidx.annotation.ArrayRes

class CurrencyParams(
    val scaleFrom: Float,
    val scaleTo: Float,
    val initValue: Int,
    @ArrayRes val stringArrayRes: Int,
    val step: Int,
    val numbersAfterPoint: Int
)