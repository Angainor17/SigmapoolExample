package com.sigmapool.common.listLibrary.pagedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

abstract class SimpleAdapter<T : BaseItemViewModel>(val itemLayoutProvider: IItemBindingHelper) :
    RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    val items = ArrayList<T>()

    override fun getItemCount(): Int = items.size

    fun getItem(i: Int) = items[i]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            itemLayoutProvider.getBindingFunction(viewType),
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemLayoutProvider.getLayoutId(viewType),
                parent,
                false
            )
        )
    }

    class ViewHolder(
        val bindFunction: (db: ViewDataBinding, vm: BaseItemViewModel) -> Unit,
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: BaseItemViewModel) {
            bindFunction(binding, viewModel)
        }
    }
}
