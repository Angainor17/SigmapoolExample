package com.sigmapool.app.screens.miningProfit.viewModels

import com.sigmapool.app.screens.earnings.params.EARNINGS_PAGE_SIZE
import com.sigmapool.common.listLibrary.HeaderListVM
import com.sigmapool.common.listLibrary.datasource.SimpleMapper
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.pagedlist.SimpleAdapter
import com.sigmapool.common.models.MinerDto

class MinerListVM(
    mapper: SimpleMapper<MinerDto, MinerItemVM>,
    loader: IItemsLoader<MinerDto>,
    adapter: SimpleAdapter<MinerItemVM>
) : HeaderListVM<MinerDto, MinerItemVM>(mapper, loader, adapter, EARNINGS_PAGE_SIZE)
