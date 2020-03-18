package org.sigmapool.common.listLibrary.viewmodel

import androidx.recyclerview.widget.DiffUtil

class BaseItemViewModelDiffCallback<T: BaseItemViewModel> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.areContentsTheSame(newItem)
}
