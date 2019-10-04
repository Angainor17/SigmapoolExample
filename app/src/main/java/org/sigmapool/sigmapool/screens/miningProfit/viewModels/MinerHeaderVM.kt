package org.sigmapool.sigmapool.screens.miningProfit.viewModels

import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.common.viewModels.IIndicatorSeekBarViewModel

class MinerHeaderVM(val indicatorSeekBarViewModel: IIndicatorSeekBarViewModel) : BaseItemViewModel {

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false
    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false

    override val itemViewType: Int = -1
}