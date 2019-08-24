package com.sigmapool.app.screens.poolInfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoBtcDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PoolInfoBtcViewModel(model: IPoolInfoBtcModel) : ViewModel(){
    val fpps = MutableLiveData<String>()
    val pps  = MutableLiveData<String>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            handleData(model.getBtcPoolInfo())
        }
    }

    fun handleData(poolInfoBtcDto: ManagerResult<PoolInfoBtcDto>) = GlobalScope.launch (Dispatchers.Main) {
        if(poolInfoBtcDto.success){
            val info = poolInfoBtcDto.data
            fpps.postValue(info?.feeFpps.toString())
            pps.postValue(info?.feePps.toString())
        } else{
            // TODO: error handling
        }
    }
}