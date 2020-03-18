package org.sigmapool.sigmapool.screens.miningProfit

import org.sigmapool.common.listLibrary.datasource.SimpleMapper
import org.sigmapool.common.models.MinerDto
import org.sigmapool.sigmapool.provider.currency.ICurrencyProvider
import org.sigmapool.sigmapool.screens.miningProfit.viewModels.MinerItemVM

class MinerItemMapper(private val currencyProvider: ICurrencyProvider) : SimpleMapper<MinerDto, MinerItemVM>() {

    override fun map(input: MinerDto): MinerItemVM = MinerItemVM(currencyProvider, input)

}