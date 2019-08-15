package com.sigmapool.app.screens.poolInfo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.screens.miningProfit.IMinerFragmentModel
import com.sigmapool.app.utils.getString
import com.sigmapool.common.managers.IPoolInfoManager
import com.sigmapool.common.viewModels.ITitleViewModel
import org.kodein.di.generic.instance


class PoolInfoViewModel(model: IPoolInfoModel) : ViewModel(), ITitleViewModel, ICurrencySwitcherViewModel {
    override fun setCurrencySelected(isSelected: Boolean) {
        // TODO: change appropriate model state
    }

    // private val poolInfoManager by kodein.instance<IPoolInfoManager>() // TODO: move to model

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.pool_info) }

}