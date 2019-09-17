package com.sigmapool.app.screens.poolInfo.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.screens.calculator.viewModel.CoinTabVM
import com.sigmapool.app.screens.poolInfo.model.IPoolInfoModel
import com.sigmapool.common.viewModels.ITitleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance


class PoolInfoViewModel(val model: IPoolInfoModel) : ViewModel(), ITitleViewModel,
    ICurrencySwitcherViewModel {

    private val res by kodein.instance<IResProvider>()

    val tabPositionLiveData = MutableLiveData<Int>()
    val isLoading = MutableLiveData<Boolean>(false)

    override val coinTabVM = CoinTabVM(tabPositionLiveData)
    override val btcSelected: ObservableBoolean = ObservableBoolean(true)

    private val btcVm = PoolInfoBtcViewModel()
    private val ltcVm = PoolInfoLtcViewModel()

    val fragments = arrayListOf(PoolInfoBtcPageFragment(btcVm), PoolInfoLtcPageFragment(ltcVm))

    init {
        onRefresh()
    }

    fun onRefresh() {
        isLoading.postValue(true)
        GlobalScope.launch(Dispatchers.Default) {
            val btcDeferred = async { btcVm.refreshData() }
            val ltcDeferred = async { ltcVm.refreshData() }

            btcDeferred.await()
            ltcDeferred.await()
            isLoading.postValue(false)
        }
    }

    override fun btcSelect() {
        btcSelected.set(true)
    }

    override fun ltcSelect() {
        btcSelected.set(false)
    }

    override fun getTitle() = MutableLiveData(res.getString(R.string.pool_info))

}