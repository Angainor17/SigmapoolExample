package org.sigmapool.common.managers

import org.sigmapool.common.models.AvgDto
import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.SubAccountDto

interface IDashboardManager {

    suspend fun subaccounts(coin: String): ManagerResult<ArrayList<SubAccountDto>>

    suspend fun avg(coin: String): ManagerResult<AvgDto>

}