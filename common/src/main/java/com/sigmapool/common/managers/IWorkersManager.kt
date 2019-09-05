package com.sigmapool.common.managers

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.WorkerDto
import com.sigmapool.common.models.WorkersStatusDto

interface IWorkersManager {

    suspend fun getWorkers(coin: String, page: Int, perPage: Int, status: String): ManagerResult<ArrayList<WorkerDto>>

    suspend fun getStatus(coin: String): ManagerResult<WorkersStatusDto>

}