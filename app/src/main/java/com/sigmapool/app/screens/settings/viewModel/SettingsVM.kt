package com.sigmapool.app.screens.settings.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import org.kodein.di.generic.instance

class SettingsVM : ViewModel() {

    private val resProvider by App.kodein.instance<IResProvider>()

    val toolbarVm = SettingsToolbarVM()

    val rusLanguage = LanguageItem(resProvider, R.string.rus)
    val btcCurrency = CurrencyItem(resProvider, R.string.rub)

    val languageLiveData = MutableLiveData(rusLanguage)
    val currencyLiveData = MutableLiveData(btcCurrency)
    val pushLiveData = MutableLiveData(false)

    val schemeLiveData = MutableLiveData(SchemeItem("PPS"))
    val limitLiveData = MutableLiveData("0.001 BTC")


    fun writeReview() {
        Log.d("", "")
    }

    fun markApp() {
        Log.d("", "")
    }

    fun exit() {
        Log.d("", "")
    }
}