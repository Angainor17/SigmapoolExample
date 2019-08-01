package com.sigmapool.app.screens.miningProfit

import androidx.databinding.ViewDataBinding
import com.sigmapool.app.R
import com.sigmapool.app.databinding.ItemViewPostBinding
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

//TODO stub implementation
//TODO try redone it with help of Kodein
class ItemBindingHelper : IItemBindingHelper {

    override fun getLayoutId(itemType: Int): Int {
        return when (itemType) {
            PostItemViewModel.itemType -> R.layout.item_view_post
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit {
        return when (itemType) {
            PostItemViewModel.itemType -> ::postBinding
            else -> throw UnsupportedOperationException()
        }
    }
}

private fun postBinding(db: ViewDataBinding, vm: BaseItemViewModel) {

    if (db is ItemViewPostBinding && vm is PostItemViewModel) {
        db.vm = vm
    }
}