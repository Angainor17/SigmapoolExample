package com.sigmapool.app.screens.earnings.viewModel

import android.graphics.Color
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.PaymentItemDto
import com.sigmapool.common.utils.formatDashDate
import com.sigmapool.common.utils.trimZeroEnd
import org.kodein.di.generic.instance
import java.util.*

class EarningsItemVM(
    coinProvider: ICoinProvider,
    private val input: PaymentItemDto
) : BaseItemViewModel {

    private val res by kodein.instance<IResProvider>()

    var date: String = input.date.formatDashDate()
    var coin: String = coinProvider.getLabel().toUpperCase()
    var coinValue: String = input.amount.trimZeroEnd()
    var status: String = ""
    var scheme: String = "PPS"//FIXME
    var statusTextColor: Int = Color.BLUE

    var lastPaymentDate: Date? = null
        set(value) {
            field = value
            status = if (value?.time ?: 0 > input.date.time) {
                statusTextColor = Color.parseColor("#A15B45B3")
                res.getString(R.string.paid)
            } else {
                statusTextColor = Color.parseColor("#9B9B9B")
                res.getString(R.string.saved_to_balance)
            }
        }

    companion object {
        val itemType = EarningsItemVM::class.hashCode()
        const val firstItemType = -2
    }

    override val itemViewType: Int = itemType
    override fun areItemsTheSame(item: BaseItemViewModel): Boolean = false
    override fun areContentsTheSame(item: BaseItemViewModel): Boolean = false
}
