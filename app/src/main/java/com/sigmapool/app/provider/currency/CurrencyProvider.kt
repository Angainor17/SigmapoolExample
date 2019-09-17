package com.sigmapool.app.provider.currency

import com.google.gson.Gson
import com.sigmapool.api.kodein.AUTH_MODE
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.currency.models.*
import com.sigmapool.app.screens.home.coin.BTC
import com.sigmapool.app.screens.home.coin.LTC
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CurrencyDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

const val CURRENCY_KEY = "currency"

class CurrencyProvider : ICurrencyProvider {

    private val poolManager by kodein.instance<IPoolManager>(AUTH_MODE)
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    private var selectedCurrency = usdCurrency

    init {
        initCurrency()
        initValues()
    }

    override fun getCurrency(): AppCurrency = selectedCurrency

    override fun getSymbol(): String = selectedCurrency.symbol

    override fun setCurrency(currency: AppCurrency) {
        selectedCurrency = currency
        jsonDataStorage.put(CURRENCY_KEY, currency)
    }

    override fun fromUsdToCurrency(value: Float): Float {
        val currencyDto = currencies[BTC]!!
        if (selectedCurrency.labelResId == usdCurrency.labelResId || currencyDto.usd == 0f) return value

        return (value / currencyDto.usd) * currencyDto.rub
    }

    override fun fromCoinToCurrency(coin: String, coinValue: Float): Float {
        initValues()

        return coinValue * getCurrencyPrice(currencies[coin]!!)
    }

    private fun initCurrency() {
        val storageCurrency = getCurrencyFromStorage()
        if (storageCurrency == null) {
            setCurrency(selectedCurrency)
        } else {
            selectedCurrency = storageCurrency
        }
    }

    private fun initCurrencyDto(coin: String) {
        if (currencies[coin]?.usd ?: 0f != 0f) return

        GlobalScope.launch(Dispatchers.IO) {
            val result = poolManager.getCurrency(coin)

            if (result.success) {
                currencies[coin] = result.data!!
            }
        }
    }

    private fun getCurrencyPrice(currencyDto: CurrencyDto) =
        when (selectedCurrency) {
            rubCurrency -> currencyDto.rub
            else -> currencyDto.usd
        }

    private fun getCurrencyFromStorage(): AppCurrency? = try {
        val json = jsonDataStorage.getJson(CURRENCY_KEY, "")
        val appCurrency = Gson().fromJson(json, AppCurrency::class.java)

        appCurrency?.labelResId = when (appCurrency.code) {
            RUB_CODE -> R.string.rub
            USD_CODE -> R.string.usd
            else -> R.string.usd
        }

        appCurrency
    } catch (e: Exception) {
        null
    }

    private fun initValues() {
        initCurrencyDto(BTC)
        initCurrencyDto(LTC)
    }
}