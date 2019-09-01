package com.sigmapool.app.screens.miningProfit.viewModels

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.models.Currency
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.common.utils.plus
import com.sigmapool.common.utils.spannableString
import com.sigmapool.common.viewModels.IIndicatorSeekBarViewModel
import org.kodein.di.generic.instance
import java.text.DecimalFormat


class SeekBarVM(private val currencyLiveData: MutableLiveData<Currency>) : IIndicatorSeekBarViewModel {

    private val res by kodein.instance<IResProvider>()

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
            res.getString(R.string.power_cost) + ": " + res.getString(R.string.kilowatt_hour_prefix) + " ",
            16,
            res.getColor(R.color.titleGray)
        ) +
                spannableString(
                    getFormattedValue(value, currencyLiveData.value!!),
                    16,
                    Color.BLACK
                ) +
                spannableString(
                    "/" + res.getString(R.string.kilowatt_hour),
                    16,
                    res.getColor(R.color.titleGray)
                )

    private fun getFormattedValue(value: Float?, currency: Currency): String =
        DecimalFormat("####0" + (if (currency.step > 0) "." else "") + "0".repeat(currency.step)).format(value)
}