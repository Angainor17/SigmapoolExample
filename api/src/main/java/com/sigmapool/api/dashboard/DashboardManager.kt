package com.sigmapool.api.dashboard

import com.sigmapool.common.managers.IDashboardManager
import com.sigmapool.common.models.AvgDto
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.SubAccountDto

internal class DashboardManager(private val service: IDashboardService) : IDashboardManager {

    override suspend fun subaccounts(coin: String): ManagerResult<ArrayList<SubAccountDto>> = try {
        ManagerResult(ArrayList(service.subaccounts(coin).map {
            SubAccountDto(
                it.name,
                it.hashrate,
                it.balance
            )
        }))
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }

    override suspend fun avg(coin: String): ManagerResult<AvgDto> = try {
        val result = service.avg(coin)
        ManagerResult(
            AvgDto(
                result.hour,
                result.day
            )
        )
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
    }
}