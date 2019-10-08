package org.sigmapool.api.dashboard

import org.sigmapool.common.managers.IDashboardManager
import org.sigmapool.common.models.AvgDto
import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.SubAccountDto

internal class DashboardManager(private val service: IDashboardService) : IDashboardManager {

    override suspend fun subaccounts(coin: String): ManagerResult<ArrayList<SubAccountDto>> = try {
        ManagerResult(ArrayList(service.subaccounts(coin).map {
            SubAccountDto(
                it.username,
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