package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.screens.settings.viewModel.CoinToolbarVM
import com.sigmapool.common.managers.IDashboardManager
import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.managers.IPoolManager
import com.sigmapool.common.managers.IWorkersManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class DashboardVM : ViewModel() {

    private val dashboardManager by kodein.instance<IDashboardManager>()
    private val earningsManager by kodein.instance<IEarningsManager>()
    private val workerManager by kodein.instance<IWorkersManager>()
    private val poolManager by kodein.instance<IPoolManager>()

    val toolbarVm = CoinToolbarVM()
    val coinProvider = toolbarVm.coinProvider

    val dashboardChartVM = DashboardChartVM(coinProvider)
    val dashboardChartInfoVM = DashboardChartInfoVM(coinProvider)
    val dashboardSubaccountsVM = DashboardSubAccountsVM(coinProvider)
    val dashboardEarningsVM = DashboardEarningsVM(coinProvider)
    val dashboardNetworkStatusVM = DashboardNetworkStatusVM(coinProvider)

    val isLoading = MutableLiveData(false)

    private var chartInfoJob: Job? = null
    private var subAccountsJob: Job? = null
    private var earningsJob: Job? = null
    private var networkJob: Job? = null

    init {
        onRefresh()
        coinProvider.addOnChangeListener { onRefresh() }
    }

    fun onRefresh() {
        cancelAll()
        isLoading.postValue(true)
        refreshAllData()
        isLoading.postValue(false)
    }

    private fun refreshAllData() {
        initChartInfo()
        initSubAccounts()
        initNetwork()
        initEarnings()
    }

    private fun initChartInfo() {
        chartInfoJob = GlobalScope.launch(Dispatchers.IO) {
            val avg = dashboardManager.avg(coinProvider.getLabel())
            if (avg.success) {
                dashboardChartInfoVM.initAvgDto(avg.data)
            }

            val workerStatus = workerManager.getStatus(coinProvider.getLabel())
            if (workerStatus.success) {
                workerStatus.data?.let { dashboardChartInfoVM.initWorkerStatus(it) }
            }

            val earningsDaily = earningsManager.earningsDaily(coinProvider.getLabel())
            if (earningsDaily.success) {
                earningsDaily.data?.let { dashboardChartInfoVM.initEarningsDaily(it) }
            }

            val balance = earningsManager.balance(coinProvider.getLabel())
            if (balance.success) {
                dashboardChartInfoVM.initBalance(balance.data)
            }
        }
    }

    private fun initEarnings() {
        val coin = coinProvider.getLabel()
        earningsJob = GlobalScope.launch(Dispatchers.IO) {
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
    }

    private fun initNetwork() {
        networkJob = GlobalScope.launch(Dispatchers.IO) {
            val network = poolManager.getNetwork(coinProvider.getLabel())
            if (network.success) {
                dashboardNetworkStatusVM.initNetworkDto(network.data!!)
            }

            val currency = poolManager.getCurrency(coinProvider.getLabel())
            if (currency.success) {
                dashboardNetworkStatusVM.initCurrency(currency.data!!)
            }
        }
    }

    private fun initSubAccounts() {
        subAccountsJob = GlobalScope.launch(Dispatchers.IO) {
            val result = dashboardManager.subaccounts(coinProvider.getLabel())
            if (result.success) {
                dashboardSubaccountsVM.initSubAccounts(result.data)
            }
        }
    }

    private fun cancelAll() {
        chartInfoJob?.cancel()
        networkJob?.cancel()
        earningsJob?.cancel()
        subAccountsJob?.cancel()
    }
}