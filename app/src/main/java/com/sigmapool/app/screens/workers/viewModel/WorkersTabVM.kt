package com.sigmapool.app.screens.workers.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.common.managers.IWorkersManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class WorkersTabVM(
    private val coinProvider: ICoinProvider,
    private val screenPositionLiveData: MutableLiveData<Int>
) : ViewModel() {

    private val workerManager by kodein.instance<IWorkersManager>()

    val onlineHashrate = MutableLiveData("0 H/s")

    val onlineCount = MutableLiveData("0")
    val offlineCount = MutableLiveData("0")
    val totalCount = MutableLiveData("0")

    init {
        initTabValues()
    }

    fun onlineTabSelected() {
        screenPositionLiveData.postValue(0)
    }

    fun offlineTabSelected() {
        screenPositionLiveData.postValue(1)
    }

    fun allTabSelected() {
        screenPositionLiveData.postValue(2)
    }

    fun initTabValues() {
        GlobalScope.launch(Dispatchers.IO) {
            val result = workerManager.getStatus(coinProvider.getLabel().toLowerCase())

            if (result.success) {
                initTab(result.data?.online ?: 0, result.data?.total ?: 0)
            } else {
                initTab(0, 0)
            }
        }
    }

    private fun initTab(online: Int, total: Int) {
        onlineCount.postValue(online.toString())
        offlineCount.postValue((total - online).toString())
        totalCount.postValue(total.toString())
    }
}
