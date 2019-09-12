package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.MutableLiveData
import com.commit451.modalbottomsheetdialogfragment.ModalBottomSheetDialogFragment
import com.commit451.modalbottomsheetdialogfragment.OptionRequest
import com.sigmapool.app.App
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.settings.ISettingsView
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.models.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class SettingsSchemeVM(
    private val coinProvider: ICoinProvider,
    private val view: ISettingsView
) {

    private val resProvider by App.kodein.instance<IResProvider>()
    private val poolManager by App.kodein.instance<IPoolManager>()

    private var coinDto: CoinDto? = null

    val schemeLiveData = MutableLiveData("")

    init {
        refresh()
    }

    fun refresh() {
        val coin = coinProvider.getLabel().toLowerCase()

        initSchemeList(coin)
        initSelectedScheme(coin)
    }

    private fun initSchemeList(coin: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val coinResult = poolManager.getCoin(coin)
            if (coinResult.success) {
                coinDto = coinResult.data
            }
        }
    }

    fun showDialog() {
        if (coinDto == null) return

        val builder = ModalBottomSheetDialogFragment.Builder()

        coinDto!!.payoutScheme.forEachIndexed { index, text ->
            builder.add(OptionRequest(index, text.toUpperCase(), R.color.transparent))
        }

        builder.header(resProvider.getString(R.string.switch_scheme))
            .show(view.fragmentManager(), PAYOUT_SCHEME_TAG)
    }

    fun setScheme(text: String) {
        schemeLiveData.postValue(text)
        sendScheme(text)
    }

    private fun initSelectedScheme(coin: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val schemeResult = poolManager.getScheme(coin)
            if (schemeResult.success) {
                schemeLiveData.postValue(schemeResult.data?.scheme)
            }
        }
    }

    private fun sendScheme(scheme: String) {
        GlobalScope.launch(Dispatchers.IO) {
            poolManager.setScheme(coinProvider.getLabel().toLowerCase(), scheme.toLowerCase())
        }
    }
}