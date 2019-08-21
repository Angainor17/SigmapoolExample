package com.sigmapool.app.screens.miningProfit.viewModels

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.R
import com.sigmapool.app.models.Currency
import com.sigmapool.app.utils.getColor
import com.sigmapool.app.utils.getString
import com.sigmapool.app.utils.plus
import com.sigmapool.app.utils.spannableString
import com.sigmapool.common.viewModels.IIndicatorSeekBarViewModel
import java.text.DecimalFormat


class SeekBarVM(private val currencyLiveData: MutableLiveData<Currency>) : IIndicatorSeekBarViewModel {

    val seekBarValueLiveData = MutableLiveData<Float>()

    override fun getStartRange() = currencyLiveData.value?.scaleFrom ?: 1
    override fun getEndRange() = currencyLiveData.value?.scaleTo ?: 12
    override fun getInitValue() = currencyLiveData.value?.initValue ?: 1
    override fun getStep() = currencyLiveData.value?.step ?: 0
    override fun getDisplayedValue() = getSeekText(getInitValue().toFloat())
    override fun getArrayRes() = currencyLiveData.value?.stringArrayRes ?: R.array.array_first_and_last_1_12

    override fun onChange(value: Float) {
        seekBarValueLiveData.postValue(value)
    }

    fun getSeekText(value: Float?): CharSequence =
        spannableString(
            getString(R.string.power_cost) + ": " + getString(R.string.kilowatt_hour_prefix) + " ",
            16,
            getColor(R.color.titleGray),
            GOOGLE_FONT_FAMILY
        ) +
                spannableString(
                    getFormattedValue(value, currencyLiveData.value!!),
                    16,
                    Color.BLACK,
                    GOOGLE_FONT_FAMILY
                ) +
                spannableString(
                    "/" + getString(R.string.kilowatt_hour),
                    16,
                    getColor(R.color.titleGray),
                    GOOGLE_FONT_FAMILY
                )

    private fun getFormattedValue(value: Float?, currency: Currency): String =
        DecimalFormat("####0" + (if (currency.step > 0) "." else "") + "0".repeat(currency.step)).format(value)
}