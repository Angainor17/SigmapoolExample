package org.sigmapool.sigmapool.provider.currency

import org.sigmapool.sigmapool.provider.currency.models.AppCurrency


interface ICurrencyProvider {

    fun getSymbol(): String

    fun getCurrency(): AppCurrency

    fun setCurrency(currency: AppCurrency)

    fun fromCoinToCurrency(coin: String, coinValue: Float): Float

    fun fromUsdToCurrency(value: Float): Float

}