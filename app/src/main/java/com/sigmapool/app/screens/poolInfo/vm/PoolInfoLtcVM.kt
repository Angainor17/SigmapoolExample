package com.sigmapool.app.screens.poolInfo.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sigmapool.app.screens.poolInfo.viewmodel.IPoolInfoLtcModel
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoLtcDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PoolInfoLtcVM(model: IPoolInfoLtcModel) : ViewModel(){
    val pps = MutableLiveData<String>()

    init {
        GlobalScope.launch(Dispatchers.Default) {
            handleData(model.getLtcPoolInfo())
        }
    }

    fun handleData(poolInfoLtcDto: ManagerResult<PoolInfoLtcDto>) = GlobalScope.launch (Dispatchers.Main) {
        if(poolInfoLtcDto.success){
            val info = poolInfoLtcDto.data
            pps.postValue(info?.feePps.toString())
        } else{
            // TODO: error handling
        }
    }
}