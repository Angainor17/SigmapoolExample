package com.sigmapool.app.provider.currency

import com.sigmapool.app.provider.currency.models.AppCurrency


interface ICurrencyProvider {

    fun getSymbol(): String

    fun getCurrency(): AppCurrency

    fun setCurrency(currency: AppCurrency)

    fun fromCoinToCurrency(coin: String, coinValue: Float): Float

    fun fromUsdToCurrency(value: Float): Float

}