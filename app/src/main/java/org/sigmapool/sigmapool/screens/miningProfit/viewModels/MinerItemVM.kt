package org.sigmapool.sigmapool.screens.miningProfit.viewModels

import android.graphics.Color
import org.kodein.di.generic.instance
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.common.models.MinerDto
import org.sigmapool.common.utils.*
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.currency.ICurrencyProvider
import org.sigmapool.sigmapool.provider.res.IResProvider
import kotlin.math.abs

class MinerItemVM(
    private val currencyProvider: ICurrencyProvider,
    private val miner: MinerDto
) : BaseItemViewModel {

    private val res by kodein.instance<IResProvider>()

    val name: String = miner.title
    val imageUrl: String = miner.image
    val hashratePower: CharSequence = createHashratePower(miner)

    var btcValue: String = createBtcValueText(0f)

    var revenuePowerCost: CharSequence = createRevenuePowerCost(0f)
    val shutdownPrice: String =
        getCurrencyLabel() + " " + miner.shutdownPrice.toCurrency().format(FLOAT_PATTERN)
    var profit = ""
    var profitValue = 0f

    init {
        initPowerCost(1f)
    }

    fun initPowerCost(powerCost: Float) {
        profitValue = miner.revenue.toCurrency() - powerCost
        profit =
            (if (profitValue < 0) "-" else "") + getCurrencyLabel() + " " + abs(profitValue).format(
                FLOAT_PATTERN
            )
        revenuePowerCost = createRevenuePowerCost(powerCost)
    }

    private fun createRevenuePowerCost(powerCost: Float): CharSequence {
        return formatValueWithPrefix(
            miner.revenue.toCurrency().format(FLOAT_PATTERN) + " / ",
            getCurrencyLabel(),
            res.getColor(R.color.titleGray)
        ) + formatValueWithPrefix(
            powerCost.format(FLOAT_PATTERN),
            getCurrencyLabel(),
            res.getColor(R.color.titleGray)
        )
    }

    companion object {
        val itemType = MinerItemVM::class.hashCode()
    }

    private fun getCurrencyLabel(): String = currencyProvider.getSymbol()

    override val itemViewType: Int = itemType

    override fun areItemsTheSame(item: BaseItemViewModel) = this == item

    override fun areContentsTheSame(item: BaseItemViewModel): Boolean {
        return item is MinerItemVM && item.name == this.name
    }

    private fun createBtcValueText(value: Float) =
        res.getString(R.string.btc_caps) + " - " + getCurrencyLabel() + " " +
                value.toCurrency().format(INT_PATTERN)

    private fun createHashratePower(miner: MinerDto): CharSequence {
        val hashrate = formatLongValue(miner.hashrate)

        return hashrate.substring(0, hashrate.length - 1).spannable(Color.BLACK) +
                hashrate.substring(hashrate.length - 1).spannable(res.getColor(R.color.titleGray)) +
                (" / " + miner.power).spannable(Color.BLACK) +
                res.getString(R.string.power_postfix).spannable(res.getColor(R.color.titleGray))
    }

    fun initCoin(value: Float) {
        btcValue = createBtcValueText(value)
    }

    private fun Float.toCurrency(): Float = currencyProvider.fromUsdToCurrency(this)
}

fun String.spannable(color: Int, textSize: Int = 15) = spannableString(
    this,
    textSize,
    color
)
