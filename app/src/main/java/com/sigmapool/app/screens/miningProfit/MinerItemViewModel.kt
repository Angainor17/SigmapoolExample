package com.sigmapool.app.screens.miningProfit

import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.Miner

class MinerItemViewModel(miner: Miner) : BaseItemViewModel {

    val name: String = miner.name
    val hashratePower: String = miner.hashrate.toString() + "T / " + miner.power + "W"
    val btcValue: String = "BTC - " + getCurrencyLabel() + " " + miner.btcValue
    val revenuePowerCost = getCurrencyLabel() + miner.revenueValue + " / " + getCurrencyLabel() + miner.powerCost
    val shutdownPrice: String = getCurrencyLabel() + " " + miner.shutdownPrice
    val profit = getCurrencyLabel() + " " + miner.profit

    companion object {
        val itemType = MinerItemViewModel::class.hashCode()
    }

    private fun getCurrencyLabel(): String {
        return "$"
    }

    override val itemViewType: Int = itemType

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean {
        return this == item
    }

    override fun areContentsTheSame(item: BaseItemViewModel): Boolean {
        return item is MinerItemViewModel && item.name == this.name
    }
}

