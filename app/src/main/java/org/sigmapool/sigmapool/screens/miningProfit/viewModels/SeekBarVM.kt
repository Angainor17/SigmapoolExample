package org.sigmapool.sigmapool.screens.miningProfit.viewModels

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import org.kodein.di.generic.instance
import org.sigmapool.common.utils.plus
import org.sigmapool.common.utils.spannableString
import org.sigmapool.common.viewModels.IIndicatorSeekBarViewModel
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.currency.models.CurrencyParams
import org.sigmapool.sigmapool.provider.res.IResProvider
import java.text.DecimalFormat


class SeekBarVM(private val currencyParamsLiveData: MutableLiveData<CurrencyParams>) :
    IIndicatorSeekBarViewModel {

    private val res by kodein.instance<IResProvider>()

    val seekBarValueLiveData = MutableLiveData<Float>()

    override fun getStartRange() = currencyParamsLiveData.value?.scaleFrom ?: 1
    override fun getEndRange() = currencyParamsLiveData.value?.scaleTo ?: 12
    override fun getInitValue() = currencyParamsLiveData.value?.initValue ?: 1
    override fun getStep() = currencyParamsLiveData.value?.step ?: 0
    override fun getDisplayedValue() = getSeekText(getInitValue().toFloat())
    override fun getArrayRes() =
        currencyParamsLiveData.value?.stringArrayRes ?: R.array.array_first_and_last_1_12

    override fun onChange(value: Float) {
        val updatedValue = updateValue(value)
        seekBarValueLiveData.postValue(updatedValue)
    }

    private fun updateValue(value: Float): Float {
        val step = currencyParamsLiveData.value?.step ?: 0
        return if (step > 0) value else value / 100
    }

    fun getSeekText(value: Float?): CharSequence =
        spannableString(
            res.getString(R.string.power_cost) + ": " + res.getString(R.string.kilowatt_hour_prefix) + " ",
            16,
            res.getColor(R.color.titleGray)
        ) +
                spannableString(
                    getFormattedValue(value ?: 0f, currencyParamsLiveData.value!!),
                    16,
                    Color.BLACK
                ) +
                spannableString(
                    "/" + res.getString(R.string.kilowatt_hour),
                    16,
                    res.getColor(R.color.titleGray)
                )

    private fun getFormattedValue(value: Float?, currencyParams: CurrencyParams): String {
        val pattern =
            "####0" + (if (currencyParams.numbersAfterPoint > 0) "." else "") + "0".repeat(
                currencyParams.numbersAfterPoint
            )
        return DecimalFormat(pattern).format(value)
    }
}