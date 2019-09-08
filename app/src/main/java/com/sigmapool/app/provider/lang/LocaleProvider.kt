package com.sigmapool.app.provider.lang

import android.content.Context
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.utils.storages.JsonDataStorage
import org.kodein.di.generic.instance
import java.util.*

const val LANGUAGE_KEY = "locale"

class LocaleProvider : ILocaleProvider {

    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    private val rusLang = LocaleItem(R.string.rus, "ru")
    private val enLang = LocaleItem(R.string.english, "en")

    private val defaultLocale = enLang

    override fun getAllLocales(): ArrayList<LocaleItem> = arrayListOf(rusLang, enLang)

    override fun getLocale(): LocaleItem {
        val storageLanguage = getFromStorage()
        if (storageLanguage != null) {
            return storageLanguage
        }
        setLocale(defaultLocale)
        return defaultLocale
    }

    override fun setUpLocale(context: Context) {
        val res = context.resources

        val displayMetrics = res.displayMetrics
        val config = res.configuration
        config.locale = Locale(getLocale().locale)
        res.updateConfiguration(config, displayMetrics)
    }

    override fun setLocale(locale: LocaleItem) {
        jsonDataStorage.put(LANGUAGE_KEY, locale)
    }

    private fun getFromStorage() = Gson().fromJson(jsonDataStorage.getJson(LANGUAGE_KEY), LocaleItem::class.java)
}