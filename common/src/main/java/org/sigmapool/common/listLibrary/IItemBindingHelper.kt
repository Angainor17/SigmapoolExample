package org.sigmapool.common.listLibrary

import androidx.databinding.ViewDataBinding
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

interface IItemBindingHelper {
    fun getLayoutId(itemType: Int): Int
    fun getBindingFunction(itemType: Int): (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit
}