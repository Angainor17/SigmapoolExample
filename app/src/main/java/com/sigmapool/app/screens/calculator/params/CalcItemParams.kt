package com.sigmapool.app.screens.calculator.params

import androidx.annotation.StringRes

class CalcItemParams(
    val coinLabel: String,
    @StringRes val hashLabelRes: Int,
    val hashrateCoefficient: Long
)
