package com.sigmapool.app.screens.poolInfo.vm

import android.text.Html
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.poolInfo.viewmodel.IPoolInfoBtcModel
import com.sigmapool.common.models.*
import com.sigmapool.common.utils.formatTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PoolInfoBtcVM(model: IPoolInfoBtcModel) : ViewModel() {
    val fpps = MutableLiveData<String>()
    val pps = MutableLiveData<String>()
    val dailyProfit = MutableLiveData<String>()
    val paymentTime = MutableLiveData<String>()
    val paymentMin = MutableLiveData<String>()
    val settlementDetails = MutableLiveData<String>()
    val stratumURLs = MutableLiveData<CharSequence>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            handlePoolInfoData(model.getBtcPoolInfo())
            handleDailyProfitData(model.getDailyProfit("btc")) // TODO: dehardcode "btc" String
            handlePaymentData(model.getPayment("btc")) // TODO: dehardcode "btc" String
            handleSettlementDetailsData(model.getSettlementDetails("btc")) // TODO: you know what to do with that "btc" String
        }
    }

    fun handlePoolInfoData(poolInfoBtcDto: ManagerResult<PoolInfoBtcDto>) = GlobalScope.launch(Dispatchers.Main) {
        //        // dbg
//        val urls: Array<String> = arrayOf("www.111.com", "www.222.ru", "http://333.net.net", "www.444.en")
//        var stratumUrl = ""
//        for(url in urls) {
//            stratumUrl += url + "\n"
//        }
//
//        stratumURLs.postValue(stratumUrl)
//        //end dbg
        if (poolInfoBtcDto.success) {
            val info = poolInfoBtcDto.data
            fpps.postValue(info?.feeFpps.toString())
            pps.postValue(info?.feePps.toString())

//            var stratumUrl = "<html><head></head><body>"
//            var stratumUrl = "<p>"
//            var stratumUrl = "<html>"
//            var stratumUrl  = "<![CDATA["
//            var stratumUrl = "<string name=\"text\">"
            var stratumUrl = ""

            for (url in info!!.stratumURLs) {
//                stratumUrl += "<p><a href=\'" + url + "\'>"+url+"</a></p>"
//                stratumUrl += "<a href=\"" + url + "\">"+url+"</a>"
//                stratumUrl+= "<b>Your variable: %1$url</b>
                stratumUrl += "<a href=\"$url\">$url</a><br>"
//                stratumUrl += url+"\n"
//                stratumUrl += "<a>"+url+"</a>\n"
            }
            stratumUrl += ""
//            stratumUrl+="</string>"
//            stratumUrl+="?]]>"
//            stratumUrl+="</p>"
//            stratumUrl+="</div>"
//            stratumUrl +="</html>"
//            stratumUrl +="<</body></html>"
            stratumURLs.postValue(Html.fromHtml(stratumUrl))
//            stratumURLs.postValue(Html.toHtml(stratumUrl.toSpannable()))

//            stratumURLs.postValue(stratumUrl)
        } else {
            // TODO: error handling
        }
    }

    fun handleDailyProfitData(dailyProfitDto: ManagerResult<DailyProfitDto>) = GlobalScope.launch(Dispatchers.Main) {
        if (dailyProfitDto.success) {
            val info = dailyProfitDto.data
            dailyProfit.postValue(String.format("%.8f", (info?.profit)))
        } else {
            // TODO: error handling
        }
    }

    fun handlePaymentData(paymentDto: ManagerResult<PaymentDto>) = GlobalScope.launch(Dispatchers.Main) {
        //        //dbg
//               paymentMin.postValue("0.005")
//        //end dbg
        if (paymentDto.success) {
            val data = paymentDto.data
            paymentTime.postValue(
                String.format(
                    "%s-%s",
                    data?.time?.from?.formatTime(),
                    data?.time?.to?.formatTime()
                )
            )
            paymentMin.postValue(String.format("%.3f", data?.min))
        } else {
            // TODO: error handling
        }
    }

    fun handleSettlementDetailsData(settlementDetailsDto: ManagerResult<SettlementDetailsDto>) = GlobalScope.launch(
        Dispatchers.Main
    ) {
        //        // dbg
//        settlementDetails.postValue("Settlement is proceeded at UTC 0:30 for yesterday's mining profit. The payment will be delayed in cases of no wallet address, address modification in last 72 hours and balance not reaching minimum payment threshold.")
//        // end dbg
        if (settlementDetailsDto.success) {
            val data = settlementDetailsDto.data
            settlementDetails.postValue(data?.settlementDetailsText)
        } else {
            // TODO: error handling
        }
    }
}