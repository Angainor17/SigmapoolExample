package com.sigmapool.common.listLibrary.pagedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModelDiffCallback

open class SimplePagedAdapter(val itemLayoutProvider: IItemBindingHelper) :
    PagedListAdapter<BaseItemViewModel, SimplePagedAdapter.ViewHolder>(BaseItemViewModelDiffCallback()) {

    var linearLayoutManager: LinearLayoutManager? = null

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

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.itemViewType ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCurrentListChanged(currentList: PagedList<BaseItemViewModel>?) {
        if (currentList != null) {
            notifyDataSetChanged()
        }
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
