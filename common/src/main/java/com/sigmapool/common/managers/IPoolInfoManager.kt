package com.sigmapool.common.managers

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.PoolInfoBtcDto
import com.sigmapool.common.models.PoolInfoLtcDto


interface IPoolInfoManager {

    suspend fun getBtcPoolInfo(): ManagerResult<PoolInfoBtcDto>
    suspend fun getLtcPoolInfo(): ManagerResult<PoolInfoLtcDto>

}