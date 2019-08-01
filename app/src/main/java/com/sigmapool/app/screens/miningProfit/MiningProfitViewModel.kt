package com.sigmapool.app.screens.miningProfit

import android.graphics.Color.BLACK
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import com.sigmapool.app.R
import com.sigmapool.app.utils.getColor
import com.sigmapool.app.utils.getString
import com.sigmapool.app.utils.plus
import com.sigmapool.app.utils.spannableString
import com.sigmapool.common.models.Currency
import com.sigmapool.common.viewModels.IIndicatorSeekBarViewModel
import com.sigmapool.common.viewModels.ITitleViewModel
import java.text.DecimalFormat

const val GOOGLE_FONT_FAMILY = "Google Sans"

class MiningProfitViewModel(model: IMiningProfitFragmentModel) : ViewModel(),
    ITitleViewModel, IIndicatorSeekBarViewModel {

    val rubCurrency = Currency(1, 6, 1, R.array.array_first_and_last_1_6, 2)
    val usdCurrency = Currency(1, 12, 1, R.array.array_first_and_last_1_12, 0)

    val currencyLiveData = MutableLiveData(usdCurrency)//TODO implement switch
    val seekLabelLiveData = MutableLiveData(getSeekText(currencyLiveData.value?.initValue?.toFloat()))

    override fun getStartRange(): LiveData<Int> = map(currencyLiveData) { it.scaleFrom }
    override fun getEndRange(): LiveData<Int> = map(currencyLiveData) { it.scaleTo }
    override fun getInitValue(): LiveData<Int> = map(currencyLiveData) { it.initValue }
    override fun getStep(): LiveData<Int> = map(currencyLiveData) { it.step }
    override fun getDisplayedValue(): LiveData<CharSequence> = seekLabelLiveData
    override fun getArrayRes(): LiveData<Int> = map(currencyLiveData) { it.stringArrayRes }

    override fun onValueChange(value: Float) {
        seekLabelLiveData.postValue(getSeekText(value))
    }

    override fun getTitle() = MutableLiveData<String>().apply { value = getString(R.string.mining_profit) }

    private fun getSeekText(value: Float?): CharSequence =
        spannableString(
            getString(R.string.power_cost) + ": " + getString(R.string.kilowatt_hour_prefix) + " ",
            16,
            getColor(R.color.titleGray),
            GOOGLE_FONT_FAMILY
        ) +
                spannableString(getFormattedValue(value, currencyLiveData.value!!), 16, BLACK, GOOGLE_FONT_FAMILY) +
                spannableString(
                    "/" + getString(R.string.kilowatt_hour),
                    16,
                    getColor(R.color.titleGray),
                    GOOGLE_FONT_FAMILY
                )

    private fun getFormattedValue(value: Float?, currency: Currency): String =
        DecimalFormat("####0" + (if (currency.step > 0) "." else "") + "0".repeat(currency.step)).format(value)
}