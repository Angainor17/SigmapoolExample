package com.sigmapool.app.screens.home.viewModel

import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.miningProfit.viewModels.IProfitBtnListener
import com.sigmapool.app.screens.miningProfit.viewModels.MiningProfitListVM

class HomeMinerVM : ViewModel(), IProfitBtnListener {

    val miningProfitVM = MiningProfitListVM()

    override fun onProfitBtnSelected(isUpSort: Boolean) {
        //TODO
    }
}