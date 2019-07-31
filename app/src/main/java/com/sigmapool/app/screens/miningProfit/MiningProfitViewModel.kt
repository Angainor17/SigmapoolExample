package com.sigmapool.app.screens.miningProfit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.common.models.Currency
import com.sigmapool.common.viewModels.IIndicatorSeekBarViewModel
import com.sigmapool.common.viewModels.ITitleViewModel


class MiningProfitViewModel(model: IMiningProfitFragmentModel) : ViewModel(),
    ITitleViewModel, IIndicatorSeekBarViewModel {

    val rubCurrency = Currency(1, 6, 1, R.array.array_first_and_last_1_6, 2)
    val usdCurrency = Currency(1, 12, 1, R.array.array_first_and_last_1_12,0)

    val currencyLiveData = MutableLiveData<Currency>().apply { value = usdCurrency }

    override fun getStartRange(): LiveData<Int> = map(currencyLiveData) { it.scaleFrom }
    override fun getEndRange(): LiveData<Int> = map(currencyLiveData) { it.scaleTo }
    override fun getInitValue(): LiveData<Int> = map(currencyLiveData) { it.initValue }
    override fun getStep(): LiveData<Int> = map(currencyLiveData) { it.step }
    override fun getCount(): LiveData<Int> = map(currencyLiveData) { it.scaleTo - it.scaleFrom }

    override fun getArrayRes(): LiveData<Int> = map(currencyLiveData) { it.stringArrayRes }

    override fun getTitle() = MutableLiveData<String>().apply { value = "Mining Profit" }

}