package com.sigmapool.app.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonDataStorage(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun <Type> put(key: String, data: Type) {
        sharedPreferences.edit().putString(key, Gson().toJson(data)).apply()
    }

    fun <Type> get(key: String): Type? =
        Gson().fromJson(sharedPreferences.getString(key, ""), object : TypeToken<Type>() {}.type)
}