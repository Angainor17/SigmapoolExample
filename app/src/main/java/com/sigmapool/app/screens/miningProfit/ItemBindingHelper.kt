package com.sigmapool.app.screens.miningProfit

import androidx.databinding.ViewDataBinding
import com.sigmapool.app.R
import com.sigmapool.app.databinding.ItemViewMinerBinding
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class ItemBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            MinerItemViewModel.itemType -> R.layout.item_view_miner
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            MinerItemViewModel.itemType -> ::postBinding
            else -> throw UnsupportedOperationException()
        }
    }
}

private fun postBinding(db: ViewDataBinding, vm: BaseItemViewModel) {
    if (db is ItemViewMinerBinding && vm is MinerItemViewModel) {
        db.vm = vm
    }
}