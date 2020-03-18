package org.sigmapool.sigmapool.screens.miningProfit.viewModels

import org.sigmapool.common.listLibrary.HeaderListVM
import org.sigmapool.common.listLibrary.datasource.SimpleMapper
import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import org.sigmapool.common.models.MinerDto
import org.sigmapool.sigmapool.screens.earnings.params.EARNINGS_PAGE_SIZE

class MinerListVM(
    mapper: SimpleMapper<MinerDto, MinerItemVM>,
    loader: IItemsLoader<MinerDto>,
    adapter: SimpleAdapter<MinerItemVM>
) : HeaderListVM<MinerDto, MinerItemVM>(mapper, loader, adapter, EARNINGS_PAGE_SIZE)
