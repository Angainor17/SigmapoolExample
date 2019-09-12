package com.sigmapool.app.screens.earnings.viewModel

import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel

class EarningsHeaderVM : BaseItemViewModel {

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false
    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false

    override val itemViewType: Int = -1
}