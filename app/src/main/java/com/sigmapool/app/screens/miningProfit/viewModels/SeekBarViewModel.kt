package com.sigmapool.app.screens.miningProfit.viewModels

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sigmapool.app.R
import com.sigmapool.app.utils.getColor
import com.sigmapool.app.utils.getString
import com.sigmapool.app.utils.plus
import com.sigmapool.app.utils.spannableString
import com.sigmapool.common.models.Currency
import com.sigmapool.common.viewModels.IIndicatorSeekBarViewModel
import java.text.DecimalFormat


class SeekBarViewModel(private val currencyLiveData: MutableLiveData<Currency>) : IIndicatorSeekBarViewModel {

    private val seekLabelLiveData = MutableLiveData(getSeekText(currencyLiveData.value?.initValue?.toFloat()))

    override fun getStartRange(): LiveData<Int> = Transformations.map(currencyLiveData) { it.scaleFrom }
    override fun getEndRange(): LiveData<Int> = Transformations.map(currencyLiveData) { it.scaleTo }
    override fun getInitValue(): LiveData<Int> = Transformations.map(currencyLiveData) { it.initValue }
    override fun getStep(): LiveData<Int> = Transformations.map(currencyLiveData) { it.step }
    override fun getDisplayedValue(): LiveData<CharSequence> = seekLabelLiveData
    override fun getArrayRes(): LiveData<Int> = Transformations.map(currencyLiveData) { it.stringArrayRes }
    override fun onValueChange(value: Float) = seekLabelLiveData.postValue(getSeekText(value))

    private fun getSeekText(value: Float?): CharSequence =
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