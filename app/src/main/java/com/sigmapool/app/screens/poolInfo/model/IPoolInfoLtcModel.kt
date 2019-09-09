package com.sigmapool.app.screens.poolInfo.model

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoLtcDto

interface IPoolInfoLtcModel {
    suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto>
}
