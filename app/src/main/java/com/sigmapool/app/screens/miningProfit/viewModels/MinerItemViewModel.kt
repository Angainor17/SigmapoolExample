package com.sigmapool.app.screens.miningProfit.viewModels

import android.graphics.Color
import com.sigmapool.app.R
import com.sigmapool.app.utils.getColor
import com.sigmapool.app.utils.getString
import com.sigmapool.app.utils.plus
import com.sigmapool.app.utils.spannableString
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.MinerDto
import com.sigmapool.common.utils.FLOAT_PATTERN
import com.sigmapool.common.utils.INT_PATTERN
import com.sigmapool.common.utils.format

class MinerItemViewModel(val miner: MinerDto) : BaseItemViewModel {

    val name: String = miner.title
    val hashratePower: CharSequence = createHashratePower(miner)

    val btcValue: String = createBtcValueText(miner)

    var revenuePowerCost = ""
    val shutdownPrice: String = getCurrencyLabel() + " " + miner.shutdownPrice
    var profit = ""

    init {
        initPowerCost(1f)
    }

    fun initPowerCost(powerCost: Float) {
        profit = getCurrencyLabel() + " " + (miner.revenueValue - powerCost).format(FLOAT_PATTERN)
        revenuePowerCost = getCurrencyLabel() + miner.revenueValue + " / " + getCurrencyLabel() + powerCost
    }

    companion object {
        val itemType = MinerItemViewModel::class.hashCode()
    }

    private fun getCurrencyLabel(): String {
        return "$"
    }

    override val itemViewType: Int = itemType

    override fun areItemsTheSame(item: BaseItemViewModel) = this == item

    override fun areContentsTheSame(item: BaseItemViewModel): Boolean {
        return item is MinerItemViewModel && item.name == this.name
    }

    private fun createBtcValueText(miner: MinerDto) =
        getString(R.string.btc_caps) + " - " + getCurrencyLabel() + " " + miner.btcValue.format(INT_PATTERN)

    private fun createHashratePower(miner: MinerDto): CharSequence {
        return miner.hashrate.toString().spannable(Color.BLACK) +
                getString(R.string.hashrate_postfix).spannable(getColor(R.color.titleGray)) +
                (" / " + miner.power).spannable(Color.BLACK) +
                getString(R.string.power_postfix).spannable(getColor(R.color.titleGray))
    }
}

fun String.spannable(color: Int, textSize: Int = 15) = spannableString(
    this,
    textSize,
    color,
    GOOGLE_FONT_FAMILY
)


