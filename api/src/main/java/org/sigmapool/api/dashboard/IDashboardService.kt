package org.sigmapool.api.dashboard

import org.sigmapool.api.dashboard.models.AvgResponse
import org.sigmapool.api.dashboard.models.SubAccountItemResponse

internal interface IDashboardService {

    suspend fun subaccounts(coin: String): ArrayList<SubAccountItemResponse>

    suspend fun avg(coin: String): AvgResponse
}

