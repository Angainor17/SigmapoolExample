package com.sigmapool.app.screens.miningProfit

import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.Miner

class MinerItemMapper : SimpleMapper<Miner, MinerItemViewModel>() {

    override fun map(input: Miner): MinerItemViewModel = MinerItemViewModel(input)

}