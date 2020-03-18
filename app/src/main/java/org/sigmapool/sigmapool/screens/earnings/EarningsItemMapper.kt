package org.sigmapool.sigmapool.screens.earnings

import org.sigmapool.common.listLibrary.datasource.SimpleMapper
import org.sigmapool.common.models.PaymentItemDto
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.earnings.viewModel.EarningsItemVM

class EarningsItemMapper(private val coinProvider: ICoinProvider) :
    SimpleMapper<PaymentItemDto, EarningsItemVM>() {

    override fun map(input: PaymentItemDto) = EarningsItemVM(coinProvider, input)
}