package com.sigmapool.app.screens.poolInfo.vm

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.app.screens.poolInfo.viewmodel.ICurrencySwitcherViewModel
import com.sigmapool.app.screens.poolInfo.viewmodel.IPoolInfoModel
import com.sigmapool.app.utils.getString
import com.sigmapool.common.viewModels.ITitleViewModel


class PoolInfoVM(model: IPoolInfoModel) : ViewModel(), ITitleViewModel, ICurrencySwitcherViewModel {

    override val btcSelected: ObservableBoolean = ObservableBoolean(true)

    override fun btcSelect() {
        btcSelected.set(true)
    }

    override fun ltcSelect() {
        btcSelected.set(false)
    }

    override fun getTitle() = MutableLiveData<String>(getString(R.string.pool_info))

}