package com.sigmapool.app.screens.poolInfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.common.models.DailyProfitDto
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoBtcDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PoolInfoBtcViewModel(model: IPoolInfoBtcModel) : ViewModel(){
    val fpps = MutableLiveData<String>()
    val pps  = MutableLiveData<String>()
    val dailyProfit = MutableLiveData<String>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            handlePoolInfoData(model.getBtcPoolInfo())
            handleDailyProfitData(model.getDailyProfit())
        }
    }

    fun handlePoolInfoData(poolInfoBtcDto: ManagerResult<PoolInfoBtcDto>) = GlobalScope.launch (Dispatchers.Main) {
        if(poolInfoBtcDto.success){
            val info = poolInfoBtcDto.data
            fpps.postValue(info?.feeFpps.toString())
            pps.postValue(info?.feePps.toString())
        } else{
            // TODO: error handling
        }
    }

    fun handleDailyProfitData(dailyProfitDto: ManagerResult<DailyProfitDto>) = GlobalScope.launch (Dispatchers.Main) {
        if(dailyProfitDto.success){
            val info = dailyProfitDto.data
            dailyProfit.postValue(String.format("%08f",(info?.profit)))
        } else{
            // TODO: error handling
        }
    }
}