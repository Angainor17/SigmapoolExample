package org.sigmapool.sigmapool.screens.poolInfo.viewmodel

import android.text.Html
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.sigmapool.common.models.*
import org.sigmapool.sigmapool.screens.poolInfo.model.IPoolInfoLtcModel
import java.text.SimpleDateFormat
import java.util.*


class PoolInfoLtcViewModel : ViewModel() {
    val pps = MutableLiveData<String>()
    val dailyProfit = MutableLiveData<String>()
    val paymentTime = MutableLiveData<String>()
    val paymentMin = MutableLiveData<String>()
    val settlementDetails = MutableLiveData<String>()
    val stratumURLs = MutableLiveData<CharSequence>()

    var model: IPoolInfoLtcModel? = null

    suspend fun refreshData() {
        if (model == null) return

        handlePoolInfoData(model!!.getLtcPoolInfo())
        handleDailyProfitData(model!!.getDailyProfit("ltc")) // TODO: dehardcode "ltc" String
        handlePaymentData(model!!.getPayment("ltc")) // TODO: dehardcode "ltc" String
        handleSettlementDetailsData(model!!.getSettlementDetails("ltc")) // TODO: you know what to do with that "ltc" String
    }

    private fun handlePoolInfoData(poolInfoLtcDto: ManagerResult<PoolInfoLtcDto>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (poolInfoLtcDto.success) {
                val info = poolInfoLtcDto.data
                pps.postValue(info?.feePps.toString() + "%")

                var stratumUrl = ""

                for (url in info!!.stratumURLs) {

                    stratumUrl += "<a href=\"$url\">$url</a><br>"
                }
                stratumUrl += ""
                stratumURLs.postValue(Html.fromHtml(stratumUrl))
            } else {
                // TODO: error handling
            }
        }

    private fun handleDailyProfitData(dailyProfitDto: ManagerResult<DailyProfitDto>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (dailyProfitDto.success) {
                val info = dailyProfitDto.data
                dailyProfit.postValue(String.format("%.8f", (info?.profit)))
            } else {
                // TODO: error handling
            }
        }

    private fun handlePaymentData(paymentDto: ManagerResult<PaymentDto>) =
        GlobalScope.launch(Dispatchers.Main) {
            if (paymentDto.success) {
                val data = paymentDto.data

                val inputDataFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                val outputDataFormat = SimpleDateFormat("HH:mm")

                inputDataFormat.timeZone = TimeZone.getTimeZone("UTC")
                val fromDate = data?.time?.from
                val toDate = data?.time?.to

                val fromStr = outputDataFormat.format(fromDate)
                val toStr = outputDataFormat.format(toDate)


                paymentTime.postValue(String.format("%s-%s", fromStr, toStr))
                paymentMin.postValue(String.format("%.3f", data?.min))
            } else {
                // TODO: error handling
            }
        }

    private fun handleSettlementDetailsData(settlementDetailsDto: ManagerResult<SettlementDetailsDto>) =
        GlobalScope.launch(
            Dispatchers.Main
        ) {
            if (settlementDetailsDto.success) {
                val data = settlementDetailsDto.data
                settlementDetails.postValue(data?.settlementDetailsText)
            } else {
                // TODO: error handling
            }
        }
}