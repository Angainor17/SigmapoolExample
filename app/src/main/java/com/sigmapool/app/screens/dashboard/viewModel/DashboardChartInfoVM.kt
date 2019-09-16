package com.sigmapool.app.screens.dashboard.viewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.app.utils.customViews.CustomSwitchVm
import com.sigmapool.app.utils.customViews.OnSwitchSelected
import com.sigmapool.common.models.AvgDto
import com.sigmapool.common.models.BalanceDto
import com.sigmapool.common.models.EarningsDto
import com.sigmapool.common.models.WorkersStatusDto
import com.sigmapool.common.utils.*
import org.kodein.di.generic.instance

class DashboardChartInfoVM(
    private val coinProvider: ICoinProvider
) : ViewModel() {

    private var avgDto = AvgDto(0f, 0f)
    private var isAvgHour = true


    private val resProvider by kodein.instance<IResProvider>()

    val customSwitchVm = CustomSwitchVm(
        resProvider.getString(R.string.one_hour),
        resProvider.getString(R.string.twenty_four_hour)
    )

    val coin = MutableLiveData<String>(coinProvider.getLabel().toUpperCase())

    val avgHashrateValue = MutableLiveData<String>(getAvgValueText())
    val avgHashratePostfix = MutableLiveData<String>(getAvgValuePostfix())

    val earnedLastValue = MutableLiveData<String>(0.0f.trimZeroEnd())

    val balanceValue = MutableLiveData<String>(0f.trimZeroEnd())

    val onlineWorker = MutableLiveData<String>(0.format(INT_PATTERN))

    val allWorker = MutableLiveData<String>(0.format(INT_PATTERN))

    init {
        customSwitchVm.clickListener = object : OnSwitchSelected {
            override fun onSelected(leftSelected: Boolean) {
                isAvgHour = leftSelected
                refreshAvgValue()
            }
        }
    }

    fun initAvgDto(data: AvgDto?) {
        if (data != null) {
            avgDto = data

            refreshAvgValue()
        }
        coin.postValue(coinProvider.getLabel().toUpperCase())
    }

    fun initEarningsDaily(earnings: EarningsDto) {
        earnedLastValue.postValue(earnings.earnings.trimZeroEnd())
        coin.postValue(coinProvider.getLabel().toUpperCase())
    }

    fun initWorkerStatus(data: WorkersStatusDto) {
        onlineWorker.postValue(data.online.format(INT_PATTERN))
        allWorker.postValue(data.total.format(INT_PATTERN))

        coin.postValue(coinProvider.getLabel().toUpperCase())
    }

    fun initBalance(data: BalanceDto?) {
        balanceValue.postValue(data?.balance?.trimZeroEnd())

        coin.postValue(coinProvider.getLabel().toUpperCase())
    }

    private fun refreshAvgValue() {
        avgHashrateValue.postValue(getAvgValueText().beforeLastChar())
        avgHashratePostfix.postValue(getAvgValuePostfix())
    }

    private fun getAvgValueText(): String = formatLongValue(getAvgValue(), FLOAT_PATTERN)

    private fun getAvgValuePostfix(): String {
        val result = formatLongValue(getAvgValue(), FLOAT_PATTERN)
        val lastChar = if (result.lastChar().isDigitsOnly()) "" else result.lastChar()

        return lastChar + resProvider.getString(R.string.hashrate_per_second)
    }

    private fun getAvgValue() = (if (isAvgHour) avgDto.hour else avgDto.day).toLong()
}