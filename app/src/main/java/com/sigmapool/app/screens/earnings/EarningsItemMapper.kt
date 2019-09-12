package com.sigmapool.app.screens.earnings

import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.screens.earnings.viewModel.EarningsItemVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.PaymentItemDto

class EarningsItemMapper(private val coinProvider: ICoinProvider) :
    SimpleMapper<PaymentItemDto, EarningsItemVM>() {

    override fun map(input: PaymentItemDto) = EarningsItemVM(coinProvider, input)
}