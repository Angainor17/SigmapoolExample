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

    val balance = MutableLiveData<String>(formatCoin(0.04357f))//FIXME
    val totalAmountPaid = MutableLiveData<String>(formatCoin(73.0373f))//FIXME
    val lastPaymentTime = MutableLiveData<String>(Date().formatDashDate())//FIXME
    val withdrawalAddress = MutableLiveData<String>("e589572cd637eeaa91bce24a878f431a")//FIXME
    val estimatedProfit = MutableLiveData<String>(formatCoin(0.007465f))//FIXME

    val coin = MutableLiveData<String>(coinProvider.getLabel())

    private fun formatCoin(value: Float) = value.trimZeroEnd()
}