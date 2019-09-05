package com.sigmapool.common.managers

import com.sigmapool.common.models.ManagerResult
import com.sigmapool.common.models.WorkerDto

interface IWorkersManager {

    suspend fun getWorkers(coin: String, page: Int, perPage: Int, status: String): ManagerResult<ArrayList<WorkerDto>>

}