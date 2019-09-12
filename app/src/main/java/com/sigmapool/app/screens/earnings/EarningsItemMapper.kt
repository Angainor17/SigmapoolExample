package com.sigmapool.app.screens.earnings

import com.sigmapool.app.screens.earnings.viewModel.EarningsItemVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.models.PaymentItemDto

class EarningsItemMapper : SimpleMapper<PaymentItemDto, EarningsItemVM>() {

    override fun map(input: PaymentItemDto) = EarningsItemVM()
}