package com.sigmapool.app.screens.earnings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sigmapool.app.R
import com.sigmapool.app.screens.earnings.EARNINGS_LIST_HEADER
import com.sigmapool.app.screens.earnings.viewModel.EarningsHeaderVM
import com.sigmapool.app.screens.earnings.viewModel.EarningsItemVM
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.pagedlist.SimpleAdapter

class EarningsListAdapter(
    private val headerVM: EarningsHeaderVM,
    itemLayoutProvider: IItemBindingHelper
) :
    SimpleAdapter<EarningsItemVM>(itemLayoutProvider) {


    override fun addItems(newItems: List<EarningsItemVM>) {
        items.addAll(newItems)
        notifyItemRangeChanged(1, newItems.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == EARNINGS_LIST_HEADER) {
            return ViewHolder(
                itemLayoutProvider.getBindingFunction(viewType),
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.earnings_header,
                    parent,
                    false
                )
            )
        }
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

    override fun getItemId(position: Int): Long {
        if (position == 0) return -1L

        return position.toLong()
    }

    override fun getItemCount(): Int = items.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return EARNINGS_LIST_HEADER

        return getItem(position - 1).itemViewType
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.bind(headerVM)
            return
        }

        getItem(position - 1).let {
            holder.bind(it)
        }
    }
}