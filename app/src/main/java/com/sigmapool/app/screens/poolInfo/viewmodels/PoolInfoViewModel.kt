package com.sigmapool.app.screens.poolInfo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.app.utils.getString
import com.sigmapool.common.viewModels.ITitleViewModel


class PoolInfoViewModel(model: IPoolInfoModel) : ViewModel(), ITitleViewModel, ICurrencySwitcherViewModel {
    override val title1: String
        get() = "Test Title"

    override fun setCurrencySelected(isSelected: Boolean) {
        // TODO: change appropriate model state
    }

    // private val poolInfoManager by kodein.instance<IPoolInfoManager>() // TODO: move to model

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.pool_info) }

}