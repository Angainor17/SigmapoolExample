package org.sigmapool.sigmapool.provider.currency.models

import androidx.annotation.StringRes

data class AppCurrency(
    @StringRes var labelResId: Int,
    val symbol: String,
    val code: String,
    val params: CurrencyParams
)