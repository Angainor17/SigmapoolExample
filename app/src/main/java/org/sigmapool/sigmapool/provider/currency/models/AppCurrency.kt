package org.sigmapool.sigmapool.provider.currency.models

data class AppCurrency(
    var label: Int,
    val symbol: String,
    val code: String,
    val params: CurrencyParams
)