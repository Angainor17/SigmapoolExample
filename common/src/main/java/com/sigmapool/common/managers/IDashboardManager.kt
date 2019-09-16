package com.sigmapool.common.managers

import com.sigmapool.common.models.AvgDto
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.SubAccountDto

interface IDashboardManager {

    suspend fun subaccounts(coin: String): ManagerResult<ArrayList<SubAccountDto>>

    suspend fun avg(coin: String): ManagerResult<AvgDto>

}