package com.sigmapool.app.screens.settings.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.lang.ILocaleProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.screens.settings.ISettingsView
import com.sigmapool.app.utils.interfaces.IUpdateScreenVm
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.app.utils.vm.ErrorHandleVm
import com.sigmapool.common.managers.ILoginManager
import com.sigmapool.common.models.AuthDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

private const val APP_EMAIL = "angainor@gmail.com"//FIXME

class SettingsVM(private val view: ISettingsView) : ErrorHandleVm(), IUpdateScreenVm {

    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()
    private val langProvider by kodein.instance<ILocaleProvider>()
    private val loginManager by kodein.instance<ILoginManager>()
    private val context by kodein.instance<Context>()

    val toolbarVm = CoinToolbarVM()


    private var selectedLang = langProvider.getLocale()

    private val rubCurrency = CurrencyItem(resProvider, R.string.rub)

    val languageLiveData = MutableLiveData(resProvider.getString(selectedLang.labelResId))
    val currencyLiveData = MutableLiveData(rubCurrency)
    val pushLiveData = MutableLiveData(false)

    val schemeLiveData = MutableLiveData(SchemeItem("PPS"))//FIXME
    val limitLiveData = MutableLiveData("0.001 BTC")//FIXME

    val isLoginLiveData = MutableLiveData(false)

    override fun update() {
        refreshAuth()
    }

    fun languageSelect() {
        val locales = langProvider.getAllLocales()
        val currentLocale = langProvider.getLocale()

        if (locales[0].locale == currentLocale.locale) {
            langProvider.setLocale(locales[1])
        } else {
            langProvider.setLocale(locales[0])
        }

        langProvider.setUpLocale(context)
        view.recreate()
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
        GlobalScope.launch(Dispatchers.Default) {
            val result = loginManager.logout()
            if (result.success) {
                jsonDataStorage.put(AUTH_KEY, null)
                update()
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    errorLiveData.value = result.error
                }
            }
        }
    }

    private fun refreshAuth() {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        val isLogin = authDto != null

        isLoginLiveData.postValue(isLogin)
        toolbarVm.titleLiveData.postValue(if (isLogin) authDto.username else resProvider.getString(R.string.demo))
    }
}