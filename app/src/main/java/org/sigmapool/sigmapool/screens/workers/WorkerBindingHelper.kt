package org.sigmapool.sigmapool.screens.workers

import androidx.databinding.ViewDataBinding
import org.sigmapool.common.listLibrary.IItemBindingHelper
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.sigmapool.BR
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.screens.workers.viewModel.WorkersListItemVM

class WorkerBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            WorkersListItemVM.itemType -> R.layout.item_view_worker
            WorkersListItemVM.headerItemType -> R.layout.worker_list_header_item
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            WorkersListItemVM.itemType -> ::postBinding
            WorkersListItemVM.headerItemType -> ::postBinding
            else -> throw UnsupportedOperationException()
        }
    }

    private fun postBinding(db: ViewDataBinding, vm: BaseItemViewModel) {
        if (vm is WorkersListItemVM) {
            db.setVariable(BR.vm, vm)
            return
        }
    }
}