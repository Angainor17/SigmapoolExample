package com.sigmapool.app.screens.earnings.viewModel

import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.utils.trimZeroEnd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EarningsHeaderVM(
    private val coinProvider: ICoinProvider,
    private val manager: IEarningsManager

) : BaseItemViewModel {

    var coinValue = MutableLiveData(coinProvider.getLabel())

    val yesterdayEarningsValue = MutableLiveData("0.00")
    val paidValue = MutableLiveData("0.00")
    val balanceValue = MutableLiveData("0.00")

    var onRefreshListener: (() -> Unit)? = null
    var job: Job? = null

    init {
        refresh()
    }

    fun refresh() {
        val coin = coinProvider.getLabel().toLowerCase()
        setInitValues(coin)

        job?.cancel()
        job = GlobalScope.launch(Dispatchers.IO) {
            balanceInit(coin)
            totalPaidInit(coin)
            earningsDailyInit(coin)
        }
    }

    private fun setInitValues(coin: String) {
        coinValue.postValue(coin)

        yesterdayEarningsValue.postValue("0.00")
        paidValue.postValue("0.00")
        balanceValue.postValue("0.00")
        refreshView()
    }

    private suspend fun balanceInit(coin: String) {
        val balance = manager.balance(coin)
        if (balance.success) {
            balanceValue.postValue(balance.data?.balance?.trimZeroEnd())
            refreshView()
        }
    }

    private suspend fun totalPaidInit(coin: String) {
        val totalPaid = manager.totalPaid(coin)
        if (totalPaid.success) {
            paidValue.postValue(totalPaid.data?.totalPaid?.trimZeroEnd())
            refreshView()
        }
    }

    private suspend fun earningsDailyInit(coin: String) {
        val earningsDaily = manager.payments(coin, 1)
        if (earningsDaily.success) {
            val list = earningsDaily.data
            if (list?.size ?: 0 > 0) {
                yesterdayEarningsValue.postValue(list?.get(0)?.amount?.trimZeroEnd())
                refreshView()
            }
        }
    }

    private fun refreshView() {
        onRefreshListener?.invoke()
    }

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false
    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false
    override val itemViewType: Int = -1
}