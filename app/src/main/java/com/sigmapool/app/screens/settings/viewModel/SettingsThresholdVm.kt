package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.screens.settings.ISettingsView
import com.sigmapool.app.utils.databindings.showEditTextAlertDialog
import com.sigmapool.app.utils.vm.AuthVm
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.utils.trimZeroEnd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


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
        thresholdLiveData.postValue(value.trimZeroEnd() + " " + coin.toUpperCase())

        GlobalScope.launch(Dispatchers.IO) {
            poolManager.setThreshold(coin, value)
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