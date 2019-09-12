package com.sigmapool.app.screens.earnings.viewModel

import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.kodein.di.generic.instance

class EarningsItemVM() : BaseItemViewModel {

    private val res by kodein.instance<IResProvider>()


    companion object {
        val itemType = EarningsItemVM::class.hashCode()
    }

    override val itemViewType: Int =
        itemType

    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false

    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false
}
