package com.sigmapool.app.screens.miningProfit

import androidx.databinding.ViewDataBinding
import com.sigmapool.app.BR
import com.sigmapool.app.R
import com.sigmapool.app.screens.miningProfit.viewModels.MinerItemViewModel
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

const val MINER_LIST_HEADER = -1

class MinerBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            MinerItemViewModel.itemType -> R.layout.item_view_miner
            MINER_LIST_HEADER -> R.layout.seek_bar
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            MinerItemViewModel.itemType -> ::postBinding
            MINER_LIST_HEADER -> ::postBinding
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getItemCount(): Int = 1

}

private fun postBinding(db: ViewDataBinding, vm: BaseItemViewModel) {
    db.setVariable(BR.vm, vm)
}