package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.app.utils.vm.AuthVm
import com.sigmapool.common.managers.IDashboardManager
import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.managers.IWorkersManager
import kotlinx.coroutines.*
import org.kodein.di.generic.instance

class DashboardVM : AuthVm() {

    private val dashboardManager by kodein.instance<IDashboardManager>(getManagerMode())
    private val earningsManager by kodein.instance<IEarningsManager>(getManagerMode())
    private val workerManager by kodein.instance<IWorkersManager>(getManagerMode())
    private val poolManager by kodein.instance<IPoolManager>(getManagerMode())

    val toolbarVm = CoinToolbarVM()
    val coinProvider = toolbarVm.coinProvider

    val dashboardChartVM = DashboardChartVM(coinProvider)
    val dashboardChartInfoVM = DashboardChartInfoVM(coinProvider)
    val dashboardSubaccountsVM = DashboardSubAccountsVM(coinProvider)
    val dashboardEarningsVM = DashboardEarningsVM(coinProvider)
    val dashboardNetworkStatusVM = DashboardNetworkStatusVM(coinProvider)

    val isLoading = MutableLiveData(false)

    private var mainJob: Job? = null

    init {
        onRefresh()
        coinProvider.addOnChangeListener { onRefresh() }
    }

    fun onRefresh() {
        cancelAll()

        refreshAllData()
    }

    private fun refreshAllData() {
        isLoading.postValue(true)

        val coin = coinProvider.getLabel().toLowerCase()
        mainJob = GlobalScope.launch(Dispatchers.IO) {
            val chartDeferred = async(Dispatchers.IO) { initChartInfo(coin) }
            val subAccountsDeferred = async(Dispatchers.IO) { initSubAccounts(coin) }
            val networkDeferred = async(Dispatchers.IO) { initNetwork(coin) }
            val earningsDeferred = async(Dispatchers.IO) { initEarnings(coin) }

            chartDeferred.await()
            subAccountsDeferred.await()
            networkDeferred.await()
            earningsDeferred.await()

            isLoading.postValue(false)
        }
    }

    private suspend fun initChartInfo(coin: String) {
        val avg = dashboardManager.avg(coin)
        if (avg.success) {
            dashboardChartInfoVM.initAvgDto(avg.data)
        }

        val workerStatus = workerManager.getStatus(coin)
        if (workerStatus.success) {
            workerStatus.data?.let { dashboardChartInfoVM.initWorkerStatus(it) }
        }

        val earningsDaily = earningsManager.earningsDaily(coin)
        if (earningsDaily.success) {
            earningsDaily.data?.let { dashboardChartInfoVM.initEarningsDaily(it) }
        }

        val balance = earningsManager.balance(coin)
        if (balance.success) {
            dashboardChartInfoVM.initBalance(balance.data)
        }
    }

    private suspend fun initEarnings(coin: String) {
        val balance = earningsManager.balance(coin)
        val totalPaid = earningsManager.totalPaid(coin)
        val lastPayment = earningsManager.getLastPayment(coin)
        val address = earningsManager.getAddress(coin)
        val estimatedProfit = earningsManager.getEstimatedProfit(coin)

        dashboardEarningsVM.initBalance(
            if (balance.success) balance.data?.balance ?: 0f else 0f
        )

        dashboardEarningsVM.initTotalAmountPaid(
            if (totalPaid.success) totalPaid.data?.totalPaid ?: 0f else 0f
        )

        if (lastPayment.success) {
            lastPayment.data?.date?.let { dashboardEarningsVM.initLastPaymentTime(it) }
        }

        dashboardEarningsVM.initWithdrawalAddress(
            if (address.success) address.data?.address ?: "" else ""
        )

        dashboardEarningsVM.initEstimatedProfit(
            if (estimatedProfit.success) estimatedProfit.data?.estimatedProfit ?: 0f else 0f
        )
    }

    private suspend fun initNetwork(coin: String) {
        val network = poolManager.getNetwork(coin)
        if (network.success) {
            dashboardNetworkStatusVM.initNetworkDto(network.data!!)
        }

        val currency = poolManager.getCurrency(coin)
        if (currency.success) {
            dashboardNetworkStatusVM.initCurrency(currency.data!!)
        }
    }

    private suspend fun initSubAccounts(coin: String) {
        val result = dashboardManager.subaccounts(coin)
        dashboardSubaccountsVM.refreshCoin()
        if (result.success) {
            result.data?.let { dashboardSubaccountsVM.initSubAccounts(it) }
        }
    }

    private fun cancelAll() {
        mainJob?.cancel()
    }
}