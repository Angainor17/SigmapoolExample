package com.sigmapool.api.dashboard

import com.sigmapool.api.dashboard.models.AvgResponse
import com.sigmapool.api.dashboard.models.SubAccountItemResponse

internal interface IDashboardService {

    suspend fun subaccounts(coin: String): ArrayList<SubAccountItemResponse>

    suspend fun avg(coin: String): AvgResponse
}

