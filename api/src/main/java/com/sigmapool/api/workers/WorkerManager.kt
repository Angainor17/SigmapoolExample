package com.sigmapool.api.workers

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IWorkersManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.WorkerDto
import com.sigmapool.common.models.WorkersStatusDto

internal class WorkerManager(serviceProvider: IApiServiceProvider) : IWorkersManager {

    private val api = serviceProvider.create(WorkersApi::class.java)

    override suspend fun getWorkers(
        coin: String,
        page: Int,
        perPage: Int,
        status: String
    ): ManagerResult<ArrayList<WorkerDto>> {
        return try {
            ManagerResult(ArrayList(api.getWorkers(coin, page, perPage, status).payload?.map {
                WorkerDto(
                    it.title,
                    it.hashrate,
                    it.hashrate24h,
                    it.isOnline
                )
            }
            ))
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
