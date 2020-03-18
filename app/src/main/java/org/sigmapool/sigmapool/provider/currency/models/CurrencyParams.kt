package org.sigmapool.sigmapool.provider.currency.models

import androidx.annotation.ArrayRes

class CurrencyParams(
    val scaleFrom: Int,
    val scaleTo: Int,
    val initValue: Int,
    @ArrayRes val stringArrayRes: Int,
    val step: Int,
    val numbersAfterPoint: Int
)