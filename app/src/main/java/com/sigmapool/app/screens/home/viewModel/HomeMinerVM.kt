package com.sigmapool.app.screens.home.viewModel

import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.miningProfit.listener.IProfitBtnListener
import com.sigmapool.app.screens.miningProfit.params.MinerListParams
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitListVM
import com.sigmapool.app.utils.interfaces.StateVM
import com.sigmapool.app.utils.interfaces.mapToViwState
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState

private const val LIST_ITEMS_COUNT = 3

class HomeMinerVM : ViewModel(), IProfitBtnListener, StateVM {

    val miningProfitVM = MiningProfitListVM(MinerListParams(LIST_ITEMS_COUNT))

    override val viewState = map(miningProfitVM.itemsVM.dataSourceFactory.loaderState, ItemsLoaderState::mapToViwState)

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        //TODO
        miningProfitVM.itemsVM.onRefresh()
    }
}