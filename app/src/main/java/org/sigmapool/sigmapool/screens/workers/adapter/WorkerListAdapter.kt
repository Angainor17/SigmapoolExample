package org.sigmapool.sigmapool.screens.workers.adapter

import org.sigmapool.common.listLibrary.IItemBindingHelper
import org.sigmapool.common.listLibrary.pagedlist.SimplePagedAdapter
import org.sigmapool.sigmapool.screens.workers.viewModel.WorkersListItemVM

class WorkerListAdapter(bindingHelper: IItemBindingHelper) : SimplePagedAdapter(bindingHelper) {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            WorkersListItemVM.headerItemType
        else
            super.getItemViewType(position)
    }
}