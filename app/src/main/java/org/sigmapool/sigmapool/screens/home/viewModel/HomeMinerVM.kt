package org.sigmapool.sigmapool.screens.home.viewModel

import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import org.sigmapool.common.listLibrary.loader.ItemsLoaderState
import org.sigmapool.sigmapool.screens.miningProfit.MiningListAdapter
import org.sigmapool.sigmapool.screens.miningProfit.listener.IProfitBtnListener
import org.sigmapool.sigmapool.screens.miningProfit.params.MinerListParams
import org.sigmapool.sigmapool.screens.miningProfit.viewModels.MiningProfitListVM
import org.sigmapool.sigmapool.utils.interfaces.StateVM
import org.sigmapool.sigmapool.utils.interfaces.mapToViwState

private const val LIST_ITEMS_COUNT = 1000
private const val ITEMS_COUNT = 3

class HomeMinerVM : ViewModel(), IProfitBtnListener, StateVM {

    val miningProfitVM = MiningProfitListVM(MinerListParams(LIST_ITEMS_COUNT))

    init {
        miningProfitVM.minerAdapter.maxItemsCount = ITEMS_COUNT
    }

    override val viewState =
        map(miningProfitVM.itemsVM.loaderState, ItemsLoaderState::mapToViwState)

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        val adapter = miningProfitVM.itemsVM.adapter as MiningListAdapter
        adapter.isUpSort = isUpSort
        adapter.items.sortByDescending {
            (if (isUpSort) 1 else -1) * it.fiatDailyNetProfit
        }
        adapter.notifyItemRangeChanged(1, adapter.items.size)
    }
}