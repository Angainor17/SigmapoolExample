package com.sigmapool.app.screens.poolInfo.viewmodel

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoBtcDto

interface IPoolInfoBtcModel {
    suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto>
}
