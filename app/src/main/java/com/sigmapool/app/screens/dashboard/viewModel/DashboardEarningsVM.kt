package com.sigmapool.app.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.common.utils.formatDashDate
import com.sigmapool.common.utils.trimZeroEnd
import java.util.*

class DashboardEarningsVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    val balance = MutableLiveData<String>(formatCoin(0.0f))
    val totalAmountPaid = MutableLiveData<String>(formatCoin(0.0f))
    val lastPaymentTime = MutableLiveData<String>("")
    val withdrawalAddress = MutableLiveData<String>("")
    val estimatedProfit = MutableLiveData<String>(formatCoin(0.0f))

    val coin = MutableLiveData<String>(coinProvider.getLabel())

    fun initBalance(value: Float) {
        balance.postValue(formatCoin(value))
        coin.postValue(coinProvider.getLabel())
    }

    fun initTotalAmountPaid(value: Float) {
        totalAmountPaid.postValue(formatCoin(value))
        coin.postValue(coinProvider.getLabel())
    }

    fun initLastPaymentTime(date: Date) {
        lastPaymentTime.postValue(date.formatDashDate())
    }

    fun initWithdrawalAddress(address: String) {
        withdrawalAddress.postValue(address)
    }

    fun initEstimatedProfit(value: Float) {
        estimatedProfit.postValue(formatCoin(value))
        coin.postValue(coinProvider.getLabel())
    }

    private fun formatCoin(value: Float) = value.trimZeroEnd()
}