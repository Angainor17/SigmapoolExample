package com.sigmapool.app.screens.earnings.viewModel

import android.graphics.Color
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.PaymentItemDto
import com.sigmapool.common.utils.formatDashDate
import com.sigmapool.common.utils.trimZeroEnd
import org.kodein.di.generic.instance

class EarningsItemVM(
    coinProvider: ICoinProvider,
    input: PaymentItemDto
) : BaseItemViewModel {

    private val res by kodein.instance<IResProvider>()

    var date: String = input.date.formatDashDate()
    var coin: String = coinProvider.getLabel().toUpperCase()
    var coinValue: String = input.amount.trimZeroEnd()
    var status: String = "Paid"//FIXME
    var scheme: String = "PPS"//FIXME
    var statusTextColor: Int = Color.BLUE


    companion object {
        val itemType = EarningsItemVM::class.hashCode()
        const val firstItemType = -2
    }

    override val itemViewType: Int = itemType
    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false
    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false
}
