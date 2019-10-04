package org.sigmapool.sigmapool.provider.lang

import android.content.Context
import com.google.gson.Gson
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.utils.storages.JsonDataStorage
import java.util.*

const val LANGUAGE_KEY = "locale"
const val RU_LOCALE = "ru"
const val EN_LOCALE = "en"

class LocaleProvider : ILocaleProvider {

    private val jsonDataStorage by kodein.instance<JsonDataStorage>()

    private val rusLang = LocaleItem(R.string.rus, RU_LOCALE)
    private val enLang = LocaleItem(R.string.english, EN_LOCALE)

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

    private fun getFromStorage(): LocaleItem? {
        val locale = Gson().fromJson(jsonDataStorage.getJson(LANGUAGE_KEY), LocaleItem::class.java)
        locale?.labelResId = when (locale.locale) {
            RU_LOCALE -> R.string.rus
            EN_LOCALE -> R.string.english
            else -> R.string.english
        }
        return locale
    }
}