package com.sigmapool.app.screens.poolInfo.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.app.utils.getString
import com.sigmapool.common.viewModels.ITitleViewModel


class PoolInfoViewModel(model: IPoolInfoModel) : ViewModel(), ITitleViewModel, ICurrencySwitcherViewModel {
    override val btcSelected: ObservableBoolean = ObservableBoolean(true)

    override val title1: String
        get() = getString(R.string.pool_info)

    override fun ltcSelect(){
        btcSelected.set(true)
    }

    override fun btcSelect(){
        btcSelected.set(false)
    }

    // private val poolInfoManager by kodein.instance<IPoolInfoManager>() // TODO: move to model

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.pool_info) }

}