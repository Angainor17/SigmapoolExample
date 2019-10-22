package org.sigmapool.sigmapool.provider.coin

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.screens.home.coin.CoinVm
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage

private const val LIST_KEY = "key"

object CoinStorage {

    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    fun getCoins(): ArrayList<CoinVm>? {
        val json = jsonDataStorage.getJson(LIST_KEY)
        if (!json.isNullOrEmpty()) {
            return Gson().fromJson<ArrayList<CoinVm>>(json)
        }
        return null
    }

    fun saveCoins(newCoins: ArrayList<CoinVm>?) {
        if (!newCoins.isNullOrEmpty()) {
            jsonDataStorage.put(LIST_KEY, newCoins)
        }
    }
}

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)