package org.sigmapool.sigmapool.screens.earnings.viewModel

import android.graphics.Color
import org.kodein.di.generic.instance
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.common.models.PaymentItemDto
import org.sigmapool.common.utils.formatDashDate
import org.sigmapool.common.utils.trimZeroEnd
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
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
    var scheme: String = input.scheme ?: ""
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
