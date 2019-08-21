package com.sigmapool.common.managers

import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.ManagerResult

interface ICoinManager {
    suspend fun getCoin(): ManagerResult<CoinDto>
}