package com.sigmapool.app.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class JsonDataStorage(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun <Type> put(key: String, data: Type) {
        sharedPreferences.edit().putString(key, Gson().toJson(data)).apply()
    }

    fun getJson(key: String, default: String = ""): String? = try {
        sharedPreferences.getString(key, "")
    } catch (e: Exception) {
        default
    }
}