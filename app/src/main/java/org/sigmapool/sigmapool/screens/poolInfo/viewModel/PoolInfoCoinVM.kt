package org.sigmapool.sigmapool.screens.poolInfo.viewModel

import android.text.Html
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import org.sigmapool.common.managers.IPoolInfoManager
import org.sigmapool.common.models.*
import org.sigmapool.sigmapool.App
import org.sigmapool.sigmapool.provider.lang.ILocaleProvider
import org.sigmapool.sigmapool.screens.poolInfo.params.PoolInfoItemParams
import java.text.SimpleDateFormat
import java.util.*


class PoolInfoCoinVM(params: PoolInfoItemParams) : ViewModel() {

    private val poolInfoManager by App.kodein.instance<IPoolInfoManager>()
    private val localeProvider by App.kodein.instance<ILocaleProvider>()

    private val coin = params.coinLabel.toLowerCase()

    val fpps = MutableLiveData<String>()
    val pps = MutableLiveData<String>()
    val dailyProfit = MutableLiveData<String>()
    val paymentTime = MutableLiveData<String>()
    val paymentMin = MutableLiveData<String>()
    val settlementDetails = MutableLiveData<String>()
    val stratumURLs = MutableLiveData<CharSequence>()

    suspend fun refreshData() {
        handlePoolInfoData(poolInfoManager.getBtcPoolInfo())
        handleDailyProfitData(poolInfoManager.getDailyProfit(coin))
        handlePaymentData(poolInfoManager.getPayment(coin))
        handleSettlementDetailsData(poolInfoManager.getSettlementDetails(coin, localeProvider.getLocale().locale))
    }

    private fun handlePoolInfoData(poolInfoBtcDto: ManagerResult<PoolInfoBtcDto>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (poolInfoBtcDto.success) {
                val info = poolInfoBtcDto.data
                fpps.postValue(info?.feeFpps.toString() + "%")
                pps.postValue(info?.feePps.toString() + "%")

                var stratumUrl = ""

                for (url in info!!.stratumURLs) {

                    stratumUrl += "<a href=\"$url\">$url</a><br>"
                }
                stratumUrl += ""
                stratumURLs.postValue(Html.fromHtml(stratumUrl))
            }
        }

    private fun handleDailyProfitData(dailyProfitDto: ManagerResult<DailyProfitDto>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (dailyProfitDto.success) {
                val info = dailyProfitDto.data
                dailyProfit.postValue(String.format("%.8f", (info?.profit)))
            }
        }

    private fun handlePaymentData(paymentDto: ManagerResult<PaymentDto>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (paymentDto.success) {
                val data = paymentDto.data

                val inputDataFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                val outputDataFormat = SimpleDateFormat("HH:mm")

                inputDataFormat.timeZone = TimeZone.getTimeZone("UTC")
                val fromDate = (data?.time?.from) ?: Date()
                val toDate = (data?.time?.to) ?: Date()

                val fromStr = outputDataFormat.format(fromDate)
                val toStr = outputDataFormat.format(toDate)


                paymentTime.postValue(String.format("%s-%s", fromStr, toStr))
                paymentMin.postValue(String.format("%.3f", data?.min))
            }
        }

    private fun handleSettlementDetailsData(settlementDetailsDto: ManagerResult<SettlementDetailsDto>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (settlementDetailsDto.success) {
                val data = settlementDetailsDto.data
                settlementDetails.postValue(data?.settlementDetailsText)
            }
        }
}