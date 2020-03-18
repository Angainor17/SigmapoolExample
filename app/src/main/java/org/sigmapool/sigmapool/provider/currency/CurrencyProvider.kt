package org.sigmapool.sigmapool.provider.currency

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.api.kodein.AUTH_MODE
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.models.CurrencyDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.CoinStorage
import org.sigmapool.sigmapool.provider.currency.models.*
import org.sigmapool.sigmapool.screens.miningProfit.viewModels.BTC
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage

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
        val currencyDto = currencyDto(BTC)!!
        if (selectedCurrency.label == usdCurrency.label || currencyDto.usd == 0f) return value

        return (value / currencyDto.usd) * currencyDto.rub
    }

    override fun fromCoinToCurrency(coin: String, coinValue: Float): Float {
        initValues()

        return coinValue * getCurrencyPrice(currencyDto(coin)!!)
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
        if (currencyDto(coin)?.usd ?: 0f != 0f) return

        GlobalScope.launch(Dispatchers.Default) {
            val result = poolManager.getCurrency(coin)

            if (result.success) {
                try {
                    currencies[coin] = result.data!!
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun currencyDto(coin: String): CurrencyDto? {
        return currencies[coin] ?: CurrencyDto()
    }

    private fun getCurrencyPrice(currencyDto: CurrencyDto) =
        when (selectedCurrency) {
            rubCurrency -> currencyDto.rub
            else -> currencyDto.usd
        }

    private fun getCurrencyFromStorage(): AppCurrency? = try {
        val json = jsonDataStorage.getJson(CURRENCY_KEY, "")
        val appCurrency = Gson().fromJson(json, AppCurrency::class.java)

        appCurrency?.label = when (appCurrency.code) {
            RUB_CODE -> R.string.rub
            USD_CODE -> R.string.usd
            else -> R.string.usd
        }

        appCurrency
    } catch (e: Exception) {
        null
    }

    private fun initValues() {
        CoinStorage.getCoins()?.forEach {
            initCurrencyDto(it.text.toLowerCase())
        }
    }
}