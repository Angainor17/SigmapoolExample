package org.sigmapool.sigmapool.utils.storages

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class JsonDataStorage(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun <Type> put(key: String, data: Type) {
        sharedPreferences.edit().putString(key, Gson().toJson(data)).apply()
    }

    fun put(key: String, data: Boolean) {
        sharedPreferences.edit().putBoolean(key, data).apply()
    }

    fun get(key: String) = sharedPreferences.getBoolean(key, false)

    fun getJson(key: String, default: String = ""): String? = try {
        sharedPreferences.getString(key, "")
    } catch (e: Exception) {
        default
    }
}