package com.sigmapool.api.workers

import android.content.Context
import com.sigmapool.common.managers.IWorkersManager
import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.WorkerDto
import com.sigmapool.common.models.WorkersStatusDto
import kotlin.random.Random

internal class StubWorkerManager(val context: Context) : IWorkersManager {

    private val onlineItemsIndexes = arrayListOf(2, 3, 8, 10, 11, 15, 20)

    override suspend fun getWorkers(
        coin: String,
        page: Int,
        perPage: Int,
        status: String
    ): ManagerResult<ArrayList<WorkerDto>> {
        val list = List(perPage) {
            WorkerDto(
                if (it % 4 == 0) "Sigmaoffice" else "Miner $it $coin",
                (Random.nextInt(10) + ((it + 1) * 1000000000000 + (Random.nextInt(100) * 10000000000))),
                Random.nextInt(10) + ((it + 1) * 4000000000000 + (Random.nextInt(100) * 30000000000)),
                status == "online" && (!onlineItemsIndexes.contains(it))
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
