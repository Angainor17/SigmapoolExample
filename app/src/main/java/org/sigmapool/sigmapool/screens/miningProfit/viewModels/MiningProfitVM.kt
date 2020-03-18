package org.sigmapool.sigmapool.screens.miningProfit.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.kodein.di.generic.instance
import org.sigmapool.common.viewModels.ITitleViewModel
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.miningProfit.IMinerFragmentModel

const val COIN_TAG = "coinValue"

class MiningProfitVM(val view: IMinerFragmentModel) : ViewModel(), ITitleViewModel, IMiningProfitToolbarVM {

    private val res by kodein.instance<IResProvider>()

    val listVM = MiningProfitListVM()

    override fun backBtnClick() {
        view.backBtnClick()
    }

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        val adapter = listVM.itemsVM.adapter
        adapter.items.sortByDescending {
            (if (isUpSort) 1 else -1) * it.profitValue
        }
        adapter.notifyItemRangeChanged(1, adapter.items.size)
    }

    override fun getTitle() = MutableLiveData(res.getString(R.string.mining_profit))

}
