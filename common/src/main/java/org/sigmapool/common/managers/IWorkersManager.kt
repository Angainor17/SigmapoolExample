package org.sigmapool.common.managers

import org.sigmapool.common.models.ManagerResult
import org.sigmapool.common.models.WorkerDto
import org.sigmapool.common.models.WorkersStatusDto

interface IWorkersManager {

    suspend fun getWorkers(coin: String, page: Int, perPage: Int, status: String): ManagerResult<ArrayList<WorkerDto>>

    suspend fun getStatus(coin: String): ManagerResult<WorkersStatusDto>

}