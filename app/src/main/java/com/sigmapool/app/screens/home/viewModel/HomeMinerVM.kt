package com.sigmapool.app.screens.home.viewModel

import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.miningProfit.listener.IProfitBtnListener
import com.sigmapool.app.screens.miningProfit.params.MinerListParams
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitListVM

private const val LIST_ITEMS_COUNT = 3

class HomeMinerVM : ViewModel(), IProfitBtnListener {

    val miningProfitVM = MiningProfitListVM(MinerListParams(LIST_ITEMS_COUNT))

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        //TODO
        miningProfitVM.itemsVM.onRefresh()
    }
}