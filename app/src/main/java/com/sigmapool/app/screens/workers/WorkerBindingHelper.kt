package com.sigmapool.app.screens.workers

import androidx.databinding.ViewDataBinding
import com.sigmapool.app.BR
import com.sigmapool.app.R
import com.sigmapool.app.screens.workers.viewModel.WorkersListItemVM
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

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