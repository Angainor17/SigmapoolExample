package com.sigmapool.api.dashboard

import com.sigmapool.api.dashboard.models.AvgResponse
import com.sigmapool.api.providers.IApiServiceProvider

internal class DashboardService(apiProvider: IApiServiceProvider) : IDashboardService {

    private val api = apiProvider.create(DashboardApi::class.java)

    override suspend fun subaccounts(coin: String) = api.subaccounts(coin).payload!!.subAccounts

    override suspend fun avg(coin: String): AvgResponse = api.avg(coin).payload!!
}

