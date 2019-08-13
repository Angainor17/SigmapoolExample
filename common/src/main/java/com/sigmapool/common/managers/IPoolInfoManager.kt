package com.sigmapool.common.managers

import com.sigmapool.common.models.PoolInfoDto
import com.sigmapool.common.models.ManagerResult


interface IPoolInfoManager {

    suspend fun getPoolInfo(): ManagerResult<PoolInfoDto>

}