package org.sigmapool.sigmapool.screens.miningProfit.viewModels

import android.graphics.Color
import org.kodein.di.generic.instance
import org.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import org.sigmapool.common.models.MinerDto
import org.sigmapool.common.utils.*
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.provider.currency.ICurrencyProvider
import org.sigmapool.sigmapool.provider.currency.models.rubCurrency
import org.sigmapool.sigmapool.provider.res.IResProvider
import org.sigmapool.sigmapool.screens.home.coin.CoinVm
import kotlin.math.abs

class MinerItemVM(
    private val currencyProvider: ICurrencyProvider,
    private val miner: MinerDto
) : BaseItemViewModel {

    private val res by kodein.instance<IResProvider>()
    private val coinProvider by kodein.instance<ICoinProvider>()

    private var selectedCoin: CoinVm

    val name: String = miner.title
    val coin: String = miner.coin
    val imageUrl: String = miner.image
    val hashratePower: CharSequence = createHashratePower(miner)

    var coinValue = ""

    private val profitability: Float
    private val currencyUsdRate: Float
    private val coinDailyGrossProfit: Float

    var fiatDailyNetProfit: Float = 0f //Profit
    private val fiatDailyGrossProfit: Float  //Revenue

    var revenuePowerCost: CharSequence = createRevenuePowerCost(0f)
    var shutdownPrice: String = ""
    var profit = ""

    init {
        val coins = coinProvider.coins.value!!
        selectedCoin = coins.find { it.text.toLowerCase() == coin }!!
        coinValue = createCoinValueText()

        profitability = selectedCoin.profitability

        val fiatRate = if (currencyProvider.getCurrency().code == rubCurrency.code) {
            selectedCoin.priceRub
        } else {
            selectedCoin.priceUsd
        }

        currencyUsdRate = selectedCoin.priceUsd / fiatRate
        coinDailyGrossProfit =
            profitability * (miner.hashrate / coinHashrateUnit(selectedCoin.unit))

        fiatDailyGrossProfit = coinDailyGrossProfit * fiatRate
    }

    fun initByPowerCost(powerCost: Float) {
        val fiatDailyPowerExpenses =
            (miner.power / 1000.0f) * (powerCost * 24f) * currencyUsdRate //Power Cost
        fiatDailyNetProfit = fiatDailyGrossProfit - (fiatDailyPowerExpenses / currencyUsdRate)

        val shutdownCoinPrice = fiatDailyPowerExpenses / coinDailyGrossProfit
        shutdownPrice = getCurrencyLabel() + " " + shutdownCoinPrice.format(FLOAT_PATTERN)

        profit =
            (if (fiatDailyNetProfit < 0) "-" else "") + getCurrencyLabel() + " " + abs(
                fiatDailyNetProfit
            ).format(
                FLOAT_PATTERN
            )
        revenuePowerCost = createRevenuePowerCost(fiatDailyPowerExpenses)
    }

    private fun createRevenuePowerCost(powerCost: Float): CharSequence {
        val revenue = fiatDailyGrossProfit.format(FLOAT_PATTERN)

        return formatValueWithPrefix(
            "$revenue / ",
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

    private fun createCoinValueText(): String {
        return coin.toUpperCase() + " - " + getCurrencyLabel() + " " +
                currencyProvider.fromUsdToCurrency(selectedCoin.priceUsd).format(FLOAT_PATTERN)
    }

    private fun createHashratePower(miner: MinerDto): CharSequence {
        val hashrate = formatLongValue(miner.hashrate)

        return hashrate.substring(0, hashrate.length - 1).spannable(Color.BLACK) +
                hashrate.substring(hashrate.length - 1).spannable(res.getColor(R.color.titleGray)) +
                (" / " + miner.power).spannable(Color.BLACK) +
                res.getString(R.string.power_postfix).spannable(res.getColor(R.color.titleGray))
    }

    fun initCoin() {
        coinValue = createCoinValueText()
    }
}

fun String.spannable(color: Int, textSize: Int = 15) = spannableString(
    this,
    textSize,
    color
)

fun coinHashrateUnit(unit: String): Float = when (unit.toLowerCase()) {
    "th/s" -> 1e12f
    "gh/s" -> 1e9f
    "mh/s" -> 1e6f
    "kh/s" -> 1e3f
    "h/s" -> 1f
    else -> 0f
}
