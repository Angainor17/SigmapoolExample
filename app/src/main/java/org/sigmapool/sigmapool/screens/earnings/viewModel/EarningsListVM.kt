package org.sigmapool.sigmapool.screens.earnings.viewModel

import org.sigmapool.common.listLibrary.HeaderListVM
import org.sigmapool.common.listLibrary.datasource.SimpleMapper
import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import org.sigmapool.common.models.PaymentItemDto
import org.sigmapool.sigmapool.screens.earnings.params.EARNINGS_PAGE_SIZE

class EarningsListVM(
    mapper: SimpleMapper<PaymentItemDto, EarningsItemVM>,
    loader: IItemsLoader<PaymentItemDto>,
    adapter: SimpleAdapter<EarningsItemVM>
) : HeaderListVM<PaymentItemDto, EarningsItemVM>(mapper, loader, adapter, EARNINGS_PAGE_SIZE)