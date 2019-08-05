package com.sigmapool.app.screens.miningProfit

import com.sigmapool.app.screens.miningProfit.viewModels.MinerItemViewModel
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.MinerDto

class MinerItemMapper : SimpleMapper<MinerDto, MinerItemViewModel>() {

    override fun map(input: MinerDto): MinerItemViewModel =
        MinerItemViewModel(input)

}