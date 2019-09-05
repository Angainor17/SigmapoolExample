package com.sigmapool.api.workers

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IWorkersManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.WorkerDto

internal class StubWorkerManager(serviceProvider: IApiServiceProvider) : IWorkersManager {

    private val api = serviceProvider.create(WorkersApi::class.java)

    override suspend fun getWorkers(
        coin: String,
        page: Int,
        perPage: Int,
        status: String
    ): ManagerResult<ArrayList<WorkerDto>> {
        val list = List(40) {
            WorkerDto(
                "Worker $it",
                it * 10000000000,
                it * 40000000000,
                it % 6 == 0
            )
        }


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
}
