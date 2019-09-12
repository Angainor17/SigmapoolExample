package com.sigmapool.app.screens.settings.viewModel

import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.utils.trimZeroEnd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class SettingsThresholdVm(
    private val coinProvider: ICoinProvider
) {

    private val poolManager by kodein.instance<IPoolManager>()

    val thresholdLiveData = MutableLiveData("")

    init {
        refresh()
    }

    fun refresh() {
        initValue()
    }

    fun thresholdSelect() {
        //TODO
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