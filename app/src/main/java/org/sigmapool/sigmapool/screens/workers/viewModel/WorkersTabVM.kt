package org.sigmapool.sigmapool.screens.workers.viewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IWorkersManager
import org.sigmapool.common.models.WorkerDto
import org.sigmapool.common.utils.INT_PATTERN
import org.sigmapool.common.utils.beforeLastChar
import org.sigmapool.common.utils.formatLongValue
import org.sigmapool.common.utils.lastChar
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.bottomSheetScreen.ViewPagerScreen
import org.sigmapool.sigmapool.utils.vm.AuthVm

class WorkersTabVM(
    private val coinProvider: ICoinProvider,
    private val screenPositionLiveData: MutableLiveData<ViewPagerScreen>
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
        screenPositionLiveData.postValue(ViewPagerScreen(0))
    }

    fun offlineTabSelected() {
        screenPositionLiveData.postValue(ViewPagerScreen(1))
    }

    fun allTabSelected() {
        screenPositionLiveData.postValue(ViewPagerScreen(2))
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
