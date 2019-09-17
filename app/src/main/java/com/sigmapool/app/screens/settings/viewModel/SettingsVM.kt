package com.sigmapool.app.screens.settings.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.commit451.modalbottomsheetdialogfragment.ModalBottomSheetDialogFragment
import com.commit451.modalbottomsheetdialogfragment.Option
import com.google.gson.Gson
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.provider.currency.models.rubCurrency
import com.sigmapool.app.provider.currency.models.usdCurrency
import com.sigmapool.app.provider.lang.ILocaleProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.login.data.AUTH_KEY
import com.sigmapool.app.screens.settings.ISettingsView
import com.sigmapool.app.utils.interfaces.IUpdateScreenVm
import com.sigmapool.app.utils.storages.JsonDataStorage
import com.sigmapool.app.utils.vm.AuthVm
import com.sigmapool.common.managers.ILoginManager
import com.sigmapool.common.models.AuthDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

private const val APP_EMAIL = "angainor@gmail.com"//FIXME

private const val LOCALE_TAG = "locale"
private const val CURRENCY_TAG = "currency"
const val PAYOUT_SCHEME_TAG = "scheme"

private const val PUSH_ENABLE = "push_notification"

class SettingsVM(private val view: ISettingsView) : AuthVm(), IUpdateScreenVm,
    ModalBottomSheetDialogFragment.Listener {

    private val resProvider by kodein.instance<IResProvider>()
    private val jsonDataStorage by kodein.instance<JsonDataStorage>()
    private val langProvider by kodein.instance<ILocaleProvider>()
    private val currencyProvider by kodein.instance<ICurrencyProvider>()
    private val loginManager by kodein.instance<ILoginManager>()
    private val context by kodein.instance<Context>()

    val toolbarVm = CoinToolbarVM()
    val settingsSchemeVM = SettingsSchemeVM(toolbarVm.coinProvider, view)
    val settingsThresholdVm = SettingsThresholdVm(toolbarVm.coinProvider, view)

    private var selectedLang = langProvider.getLocale()

    val localeLiveData = MutableLiveData(resProvider.getString(selectedLang.labelResId))
    val currencyLiveData =
        MutableLiveData(resProvider.getString(currencyProvider.getCurrency().labelResId))
    val pushLiveData = MutableLiveData(getPushEnable())

    val isLoginLiveData = MutableLiveData(false)

    init {
        toolbarVm.coinProvider.addOnChangeListener {
            settingsSchemeVM.refresh()
            settingsThresholdVm.refresh()
        }
    }

    override fun update() {
        refreshAuth()
    }

    override fun onModalOptionSelected(tag: String?, option: Option) {
        if (tag == LOCALE_TAG) changeLocale(option)
        if (tag == CURRENCY_TAG) changeCurrency(option)
        if (tag == PAYOUT_SCHEME_TAG) settingsSchemeVM.setScheme(option.title.toString())
    }

    fun localeSelect() {
        ModalBottomSheetDialogFragment.Builder()
            .add(R.menu.locale_menu)
            .header(resProvider.getString(R.string.switch_language))
            .show(view.fragmentManager(), LOCALE_TAG)
    }

    fun currencySelect() {
        ModalBottomSheetDialogFragment.Builder()
            .add(R.menu.currency_menu)
            .header(resProvider.getString(R.string.switch_currency))
            .show(view.fragmentManager(), CURRENCY_TAG)
    }

    fun pushSwitch(isChecked: Boolean) {
        jsonDataStorage.put(PUSH_ENABLE, isChecked)
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
                logoutAction()
            } else {
                if ((result.error ?: "").contains("HTTP")) {
                    logoutAction()
                    return@launch
                }

                CoroutineScope(Dispatchers.Main).launch {
                    errorLiveData.value = result.error
                }
            }
        }
    }

    private fun logoutAction() {
        jsonDataStorage.put(AUTH_KEY, null)
        view.recreate()
    }

    private fun changeCurrency(option: Option) {
        val selectedCurrency = if (option.id == R.id.currency_rub) rubCurrency else usdCurrency

        currencyProvider.setCurrency(selectedCurrency)
        currencyLiveData.postValue(resProvider.getString(selectedCurrency.labelResId))
        view.recreate()
    }

    private fun changeLocale(option: Option) {
        val locales = langProvider.getAllLocales()

        val selectedLocale = locales[if (option.id == R.id.locale_rus) 0 else 1]
        val currentLocale = langProvider.getLocale()

        if (currentLocale == selectedLocale) return

        langProvider.setLocale(locales[if (locales[0].locale == currentLocale.locale) 1 else 0])
        langProvider.setUpLocale(context)

        view.recreate()
    }

    private fun getPushEnable(): Boolean = jsonDataStorage.get(PUSH_ENABLE)

    private fun refreshAuth() {
        val authDto = Gson().fromJson(jsonDataStorage.getJson(AUTH_KEY), AuthDto::class.java)
        val isLogin = authDto != null

        isLoginLiveData.postValue(isLogin)
        toolbarVm.titleLiveData.postValue(if (isLogin) authDto.username else resProvider.getString(R.string.demo))
    }
}