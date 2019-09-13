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
import java.util.*

class EarningsListAdapter(
    private val headerVM: EarningsHeaderVM,
    itemLayoutProvider: IItemBindingHelper
) :
    SimpleAdapter<EarningsItemVM>(itemLayoutProvider) {

    var lastPaymentDate: Date? = null
        set(value) {
            field = value
            initLastPayment(value)
            notifyItemRangeChanged(1, items.size)
        }

    override fun addItems(newItems: List<EarningsItemVM>) {
        items.addAll(newItems)
        initLastPayment(lastPaymentDate)

        notifyItemRangeChanged(1, newItems.size)
    }

    private fun initLastPayment(value: Date?) {
        items.forEach {
            it.lastPaymentDate = value
        }
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

    override fun getItemId(position: Int): Long =
        if (position == 0) -1L else if (position == 1) -2L else position.toLong()

    override fun getItemCount(): Int = items.size + 1

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> -1
        1 -> EarningsItemVM.firstItemType
        else -> getItem(position - 1).itemViewType
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