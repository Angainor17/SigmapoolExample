package com.sigmapool.common.listLibrary

import androidx.databinding.ViewDataBinding
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

interface IItemBindingHelper {
    fun getLayoutId(itemType: Int): Int
    fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit
    fun getItemCount(): Int
}