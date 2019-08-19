package com.sigmapool.app.screens.miningProfit.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.models.Currency
import com.sigmapool.app.screens.miningProfit.IMinerFragmentModel
import com.sigmapool.app.screens.miningProfit.MinerBindingHelper
import com.sigmapool.app.screens.miningProfit.MinerItemMapper
import com.sigmapool.app.screens.miningProfit.MinerLoader
import com.sigmapool.app.utils.getString
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedListViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.viewModels.ITitleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

const val GOOGLE_FONT_FAMILY = "Google Sans"//FIXME

class MiningProfitViewModel(model: IMinerFragmentModel) : ViewModel(), ITitleViewModel, IMiningProfitToolbarViewModel {

    private val usdCurrency = Currency(1, 12, 1, R.array.array_first_and_last_1_12, 0)

    private val currencyLiveData = MutableLiveData(usdCurrency)
    val seekBarVM = SeekBarViewModel(currencyLiveData)

    private val minerManager by kodein.instance<IMinerManager>()
    val itemsVM: SimplePagedListViewModel<BaseItemViewModel, Any>

    init {
        itemsVM = SimplePagedListViewModel(
            MinerItemMapper(),
            MinerLoader(minerManager),
            MinerBindingHelper()
        ) as SimplePagedListViewModel<BaseItemViewModel, Any>

//        itemsVM.pagedRecyclerAdapter.currentList?.add(MinerHeaderVM())//submitList()
    }

    val seekBarLiveData = map(seekBarVM.seekBarValueLiveData) { value ->
        GlobalScope.launch(Dispatchers.Default) {
            setProfitValue(value)
        }

        GlobalScope.launch(Dispatchers.Main) {
            itemsVM.pagedRecyclerAdapter.notifyItemRangeChanged(0, itemsVM.pagedRecyclerAdapter.itemCount)
        }
    }

    private fun setProfitValue(value: Float) {
        itemsVM.pagedRecyclerAdapter.currentList?.forEach {
            (it as MinerItemViewModel).initPowerCost(value)
            Log.d("voronin", "Name " + it.name + " = $value")
        }
    }


    override fun onProfitBtnSelected(isSelected: Boolean) {

    }

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.mining_profit) }

}