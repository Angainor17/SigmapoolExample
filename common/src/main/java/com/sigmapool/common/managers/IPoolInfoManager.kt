package com.sigmapool.common.managers

import com.sigmapool.common.models.PoolInfoBtcDto
import com.sigmapool.common.models.ManagerResult


interface IPoolInfoManager {

    suspend fun getPoolInfo(): ManagerResult<PoolInfoBtcDto>

}