package org.sigmapool.sigmapool.screens.settings.viewModel

import androidx.lifecycle.MutableLiveData
import com.commit451.modalbottomsheetdialogfragment.ModalBottomSheetDialogFragment
import com.commit451.modalbottomsheetdialogfragment.OptionRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.models.CoinInfoDto
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.settings.ISettingsView
import org.sigmapool.sigmapool.utils.vm.AuthVm

class SettingsSchemeVM(
    private val coinProvider: ICoinProvider,
    private val view: ISettingsView
) : AuthVm() {

    private val resProvider by kodein.instance<IResProvider>()
    private val poolManager by kodein.instance<IPoolManager>(getManagerMode())

    private var coinDto: CoinInfoDto? = null

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
            val result = poolManager.setScheme(
                coinProvider.getLabel().toLowerCase(),
                scheme.toUpperCase()
            )
            if (result.success) {
                schemeLiveData.postValue(scheme)
            }
        }
    }
}