package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.settings.ISettingsView
import org.kodein.di.generic.instance

private const val APP_EMAIL = "angainor17@gmail.com"//FIXME

class SettingsVM(private val view: ISettingsView) : ViewModel() {

    private val resProvider by App.kodein.instance<IResProvider>()

    val toolbarVm = SettingsToolbarVM()

    val rusLanguage = LanguageItem(resProvider, R.string.rus)//FIXME
    val btcCurrency = CurrencyItem(resProvider, R.string.rub)//FIXME

    val languageLiveData = MutableLiveData(rusLanguage)
    val currencyLiveData = MutableLiveData(btcCurrency)
    val pushLiveData = MutableLiveData(false)

    val schemeLiveData = MutableLiveData(SchemeItem("PPS"))//FIXME
    val limitLiveData = MutableLiveData("0.001 BTC")//FIXME

    val isLoginLiveData = MutableLiveData(false)//FIXME


    fun languageSelect() {
        //TODO implement
    }

    fun currencySelect() {
        //TODO implement
    }

    fun pushSwitch(isChecked: Boolean) {
        //TODO implement
    }

    fun schemeSelect() {
        //TODO implement
    }

    fun limitSelect() {
        //TODO implement
    }

    fun writeReview() {
        view.sendEmail(APP_EMAIL)
    }

    fun markApp() {
        view.markApp()
    }

    fun exit() {
        //TODO implement
    }
}