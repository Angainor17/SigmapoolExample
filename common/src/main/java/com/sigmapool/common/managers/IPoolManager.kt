package com.sigmapool.common.managers

import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.ManagerResult

interface IPoolManager {
    suspend fun getCoin(): ManagerResult<CoinDto>
}