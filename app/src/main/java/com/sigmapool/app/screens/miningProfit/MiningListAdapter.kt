package com.sigmapool.app.screens.miningProfit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.sigmapool.app.R
import com.sigmapool.app.screens.miningProfit.viewModels.MinerHeaderVM
import com.sigmapool.app.screens.miningProfit.viewModels.MinerItemVM
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import com.sigmapool.common.models.CoinDto
import kotlin.math.min

class MiningListAdapter(
    private val minerHeaderVM: MinerHeaderVM,
    itemLayoutProvider: IItemBindingHelper
) :
    SimpleAdapter<MinerItemVM>(itemLayoutProvider) {

    var maxItemsCount: Int = -1
    private var powerCostTv: TextView? = null
    var coinInfo: CoinDto? = null


    fun setPowerCost(text: CharSequence) {
        powerCostTv?.text = text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == MINER_LIST_HEADER) {
            return ViewHolder(
                itemLayoutProvider.getBindingFunction(viewType),
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.seek_bar,
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

    override fun getItemCount(): Int {
        return if (maxItemsCount > 0) {
            min(maxItemsCount, items.size) + 1
        } else {
            items.size + 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return MINER_LIST_HEADER

        return getItem(position - 1).itemViewType
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            powerCostTv = holder.itemView.findViewById<TextView>(R.id.powerCostTv)
            holder.bind(minerHeaderVM)
            return
        }

        getItem(position - 1).let {
            coinInfo.let { coin ->
                it.initCoin(coin?.price ?: 0f)
            }

            holder.bind(it)
        }
    }
}