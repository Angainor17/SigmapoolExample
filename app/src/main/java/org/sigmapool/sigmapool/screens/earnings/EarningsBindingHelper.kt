package org.sigmapool.sigmapool.screens.earnings

import androidx.databinding.ViewDataBinding
import org.sigmapool.common.listLibrary.IItemBindingHelper
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.sigmapool.BR
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.screens.earnings.viewModel.EarningsHeaderVM
import org.sigmapool.sigmapool.screens.earnings.viewModel.EarningsItemVM

const val EARNINGS_LIST_HEADER = -1

class EarningsBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            EarningsItemVM.itemType -> R.layout.item_view_earnings
            EarningsItemVM.firstItemType -> R.layout.item_view_earnings_first
            EARNINGS_LIST_HEADER -> R.layout.earnings_header
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            EarningsItemVM.itemType -> ::postBinding
            EarningsItemVM.firstItemType -> ::postBinding
            EARNINGS_LIST_HEADER -> ::postBinding
            else -> throw UnsupportedOperationException()
        }
    }

    private fun postBinding(db: ViewDataBinding, vm: BaseItemViewModel) {
        if (vm is EarningsItemVM) {
            db.setVariable(BR.vm, vm)
            return
        }

        if (vm is EarningsHeaderVM) {
            db.setVariable(BR.vm, vm)
        }
    }
}