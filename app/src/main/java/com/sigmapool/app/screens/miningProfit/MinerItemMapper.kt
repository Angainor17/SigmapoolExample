package com.sigmapool.app.screens.miningProfit

import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.screens.miningProfit.viewModels.MinerItemVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.MinerDto

class MinerItemMapper(private val currencyProvider: ICurrencyProvider) : SimpleMapper<MinerDto, MinerItemVM>() {

    override fun map(input: MinerDto): MinerItemVM =
        MinerItemVM(currencyProvider, input)

}