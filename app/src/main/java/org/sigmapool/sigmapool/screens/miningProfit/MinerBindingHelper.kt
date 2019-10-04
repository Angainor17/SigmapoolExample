package org.sigmapool.sigmapool.screens.miningProfit

import androidx.databinding.ViewDataBinding
import org.sigmapool.common.listLibrary.IItemBindingHelper
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.sigmapool.BR
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.screens.miningProfit.viewModels.MinerHeaderVM
import org.sigmapool.sigmapool.screens.miningProfit.viewModels.MinerItemVM

const val MINER_LIST_HEADER = -1

class MinerBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            MinerItemVM.itemType -> R.layout.item_view_miner
            MINER_LIST_HEADER -> R.layout.seek_bar
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            MinerItemVM.itemType -> ::postBinding
            MINER_LIST_HEADER -> ::postBinding
            else -> throw UnsupportedOperationException()
        }
    }

    private fun postBinding(db: ViewDataBinding, vm: BaseItemViewModel) {
        if (vm is MinerItemVM) {
            db.setVariable(BR.vm, vm)
            return
        }

        if (vm is MinerHeaderVM) {
            db.setVariable(BR.vm, vm.indicatorSeekBarViewModel)
        }
    }
}