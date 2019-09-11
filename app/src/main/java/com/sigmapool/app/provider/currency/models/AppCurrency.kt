package com.sigmapool.app.provider.currency.models

import androidx.annotation.StringRes

data class AppCurrency(
    @StringRes val labelResId: Int,
    val symbol: String,
    val params: CurrencyParams
)