package org.sigmapool.api.workers

import org.sigmapool.api.providers.IApiServiceProvider
import org.sigmapool.common.managers.IWorkersManager
import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.WorkerDto
import org.sigmapool.common.models.WorkersStatusDto

internal class WorkerManager(serviceProvider: IApiServiceProvider) : IWorkersManager {

    private val api = serviceProvider.create(WorkersApi::class.java)

    override suspend fun getWorkers(
        coin: String,
        page: Int,
        perPage: Int,
        status: String
    ): ManagerResult<ArrayList<WorkerDto>> {
        return try {
            ManagerResult(
                ArrayList(
                    api.getWorkers(coin, page, perPage, status).payload?.workers?.map {
                        WorkerDto(
                            it.title,
                            it.hashrate.toLong(),
                            it.hashrate24h.toLong(),
                            it.isOnline
                        )
                    } ?: ArrayList()
                )
            )
        } catch (e: Throwable) {
            ManagerResult(error = e.message)
        }
    }

    override suspend fun getStatus(coin: String): ManagerResult<WorkersStatusDto> {
        return try {
            val response = api.getStatus(coin).payload!!
            ManagerResult(WorkersStatusDto(response.total, response.online))
        } catch (e: Throwable) {
            ManagerResult(error = e.message)
        }
    }
}
