package com.sigmapool.app.screens.earnings

import androidx.databinding.ViewDataBinding
import com.sigmapool.app.BR
import com.sigmapool.app.R
import com.sigmapool.app.screens.earnings.viewModel.EarningsHeaderVM
import com.sigmapool.app.screens.earnings.viewModel.EarningsItemVM
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

const val EARNINGS_LIST_HEADER = -1

class EarningsBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            EarningsItemVM.itemType -> R.layout.item_view_earnings
            EARNINGS_LIST_HEADER -> R.layout.earnings_header
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            EarningsItemVM.itemType -> ::postBinding
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