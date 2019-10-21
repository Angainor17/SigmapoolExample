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
        var json = jsonDataStorage.getJson(LIST_KEY)
        if (!json.isNullOrEmpty()) {
            json = json.trimStart('"').trimEnd('"')
            val items = Gson().fromJson<ArrayList<CoinVm>>(json)
            return items
        }
        return null
    }

    fun saveCoins(newCoins: ArrayList<CoinVm>?) {
        if (!newCoins.isNullOrEmpty()) {
            jsonDataStorage.put(LIST_KEY, Gson().toJson(newCoins))
        }
    }
}

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)