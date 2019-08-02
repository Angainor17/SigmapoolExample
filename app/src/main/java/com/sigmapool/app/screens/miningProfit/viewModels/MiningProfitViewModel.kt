package com.sigmapool.app.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.app.screens.miningProfit.IMinerFragmentModel
import com.sigmapool.app.screens.miningProfit.MinerItemMapper
import com.sigmapool.app.screens.miningProfit.MinerLoader
import com.sigmapool.app.utils.getString
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.Currency
import com.sigmapool.common.viewModels.ITitleViewModel

const val GOOGLE_FONT_FAMILY = "Google Sans"

class MiningProfitViewModel(model: IMinerFragmentModel) : ViewModel(), ITitleViewModel, IMiningProfitToolbarViewModel {

    private val rubCurrency = Currency(1, 6, 1, R.array.array_first_and_last_1_6, 2)
    private val usdCurrency = Currency(1, 12, 1, R.array.array_first_and_last_1_12, 0)

    private val currencyLiveData = MutableLiveData(usdCurrency)
    val seekBarVM = SeekBarViewModel(currencyLiveData)

    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any> = SimplePagedListViewModel(
        MinerItemMapper(),
        MinerLoader()
    ) as SimplePagedListViewModel<BaseItemViewModel, Any>

    override fun onCurrencyBtnSelected(isSelected: Boolean) {
        currencyLiveData.postValue(if (isSelected) usdCurrency else rubCurrency)
    }

    override fun onProfitBtnSelected(isSelected: Boolean) {

    }

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.mining_profit) }

}