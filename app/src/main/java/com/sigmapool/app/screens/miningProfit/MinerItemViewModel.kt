package com.sigmapool.app.screens.miningProfit

import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.Miner

class MinerItemViewModel(dto: Miner) : BaseItemViewModel {

    val id = dto.id
    val title = dto.title
    val text = dto.text

    companion object {
        val itemType = MinerItemViewModel::class.hashCode()
    }

    override val itemViewType: Int = itemType

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean {
        return this == item
    }

    override fun areContentsTheSame(item: BaseItemViewModel): Boolean {
        return item is MinerItemViewModel && item.id == this.id
    }
}

