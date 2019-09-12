package com.sigmapool.app.screens.earnings.viewModel

import com.sigmapool.app.screens.earnings.params.EARNINGS_PAGE_SIZE
import com.sigmapool.common.listLibrary.HeaderListVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import com.sigmapool.common.models.PaymentItemDto

class EarningsListVM(
    mapper: SimpleMapper<PaymentItemDto, EarningsItemVM>,
    loader: IItemsLoader<PaymentItemDto>,
    adapter: SimpleAdapter<EarningsItemVM>
) : HeaderListVM<PaymentItemDto, EarningsItemVM>(mapper, loader, adapter, EARNINGS_PAGE_SIZE)