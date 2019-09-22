package com.sigmapool.api.workers

import android.content.Context
import com.sigmapool.common.managers.IWorkersManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.WorkerDto
import com.sigmapool.common.models.WorkersStatusDto

internal class StubWorkerManager(val context: Context) : IWorkersManager {

    override suspend fun getWorkers(
        coin: String,
        page: Int,
        perPage: Int,
        status: String
    ): ManagerResult<ArrayList<WorkerDto>> {
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
        return ManagerResult(
            data = WorkersStatusDto(
                if (coin == "btc") 1000 else 200,
                if (coin == "btc") 123 else 17
            )
        )
    }
}
