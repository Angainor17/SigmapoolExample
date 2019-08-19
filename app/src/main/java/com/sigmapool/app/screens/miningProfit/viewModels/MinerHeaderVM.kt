package com.sigmapool.app.screens.miningProfit.viewModels

import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class MinerHeaderVM : BaseItemViewModel {

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false
    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false

    override val itemViewType: Int = -1
}