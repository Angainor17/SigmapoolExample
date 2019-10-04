package org.sigmapool.sigmapool.screens.settings.viewModel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IPoolManager
import org.sigmapool.common.utils.trimZeroEnd
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.settings.ISettingsView
import org.sigmapool.sigmapool.utils.databindings.showEditTextAlertDialog
import org.sigmapool.sigmapool.utils.vm.AuthVm


class SettingsThresholdVm(
    private val coinProvider: ICoinProvider,
    private val view: ISettingsView
) : AuthVm() {

    private val poolManager by kodein.instance<IPoolManager>(getManagerMode())

    val thresholdLiveData = MutableLiveData("")

    init {
        refresh()
    }

    fun refresh() {
        initValue()
    }

    fun thresholdClick() {
        showEditTextAlertDialog(view.context(), this::thresholdSelect)
    }

    private fun thresholdSelect(value: Float) {
        val coin = coinProvider.getLabel()

        GlobalScope.launch(Dispatchers.IO) {
            val result = poolManager.setThreshold(coin, value)

            if (result.success) {
                thresholdLiveData.postValue(value.trimZeroEnd() + " " + coin.toUpperCase())
            }
        }
    }

    private fun initValue() {
        val coin = coinProvider.getLabel()
        GlobalScope.launch(Dispatchers.IO) {
            val result = poolManager.getThreshold(coin.toLowerCase())
            val value = if (result.success) result.data?.threshold ?: 0f else 0.0f

            thresholdLiveData.postValue(value.trimZeroEnd() + " " + coin.toUpperCase())
        }
    }
}