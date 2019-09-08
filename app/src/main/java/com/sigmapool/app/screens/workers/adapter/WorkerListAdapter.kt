package com.sigmapool.app.screens.workers.adapter

import com.sigmapool.app.screens.workers.viewModel.WorkersListItemVM
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedAdapter

class WorkerListAdapter(bindingHelper: IItemBindingHelper) : SimplePagedAdapter(bindingHelper) {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            WorkersListItemVM.headerItemType
        else
            super.getItemViewType(position)
    }
}