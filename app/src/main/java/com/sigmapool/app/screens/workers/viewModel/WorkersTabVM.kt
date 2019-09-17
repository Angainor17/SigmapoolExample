package com.sigmapool.app.screens.workers.viewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.vm.AuthVm
import com.sigmapool.common.managers.IWorkersManager
import com.sigmapool.common.models.WorkerDto
import com.sigmapool.common.utils.INT_PATTERN
import com.sigmapool.common.utils.beforeLastChar
import com.sigmapool.common.utils.formatLongValue
import com.sigmapool.common.utils.lastChar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class WorkersTabVM(
    private val coinProvider: ICoinProvider,
    private val screenPositionLiveData: MutableLiveData<Int>
) : AuthVm() {

    private val workerManager by kodein.instance<IWorkersManager>(getManagerMode())
    private val res by kodein.instance<IResProvider>()

    val onlineHashrate = MutableLiveData("0 " + res.getString(R.string.hashrate_per_second))

    val onlineCount = MutableLiveData("0")
    val offlineCount = MutableLiveData("0")
    val totalCount = MutableLiveData("0")

    fun initOnlineHashrate(list: ArrayList<WorkerDto>) {
        val hashrateSum = list.sumByDouble { it.hashrate.toDouble() }.toFloat()

        val result = formatLongValue(hashrateSum.toLong(), INT_PATTERN)

        onlineHashrate.postValue(
            result.beforeLastChar() + " " + if (result.lastChar().isDigitsOnly()) "" else result.lastChar() +
                    res.getString(R.string.hashrate_per_second)
        )
    }

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
