package com.sigmapool.common.managers

import com.sigmapool.common.models.CoinDto
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.NetworkDto
import com.sigmapool.common.models.PaymentDto

interface IPoolManager {

    suspend fun getCoin(): ManagerResult<CoinDto>

    suspend fun getPayment(): ManagerResult<PaymentDto>

    suspend fun getNetwork(): ManagerResult<NetworkDto>
}