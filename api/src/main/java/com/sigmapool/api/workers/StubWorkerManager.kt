package com.sigmapool.api.workers

import com.sigmapool.api.providers.IApiServiceProvider
import com.sigmapool.common.managers.IWorkersManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.WorkerDto
import com.sigmapool.common.models.WorkersStatusDto
import kotlinx.coroutines.delay

internal class StubWorkerManager(serviceProvider: IApiServiceProvider) : IWorkersManager {

    override suspend fun getWorkers(
        coin: String,
        page: Int,
        perPage: Int,
        status: String
    ): ManagerResult<ArrayList<WorkerDto>> {

        delay(1000)

        val list = List(perPage) {
            WorkerDto(
                "Worker $it $coin",
                (it + 1) * 10000000000,
                (it + 1) * 40000000000,
                status == "online"
            )
        }
        return ManagerResult(data = ArrayList(list))
    }

    override suspend fun getStatus(coin: String): ManagerResult<WorkersStatusDto> {
        delay(2500)

        return ManagerResult(data = WorkersStatusDto(1000,7))
    }
}
