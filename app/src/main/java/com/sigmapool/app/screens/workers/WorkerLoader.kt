package com.sigmapool.app.screens.workers

import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.provider.coin.ICoinProvider
import com.sigmapool.app.screens.workers.params.WorkerListParams
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.managers.IWorkersManager
import com.sigmapool.common.models.WorkerDto
import org.kodein.di.generic.instance

class WorkerLoader(
    private val params: WorkerListParams,
    private val coinProvider: ICoinProvider
) : IItemsLoader<WorkerDto> {

    val workerManager by kodein.instance<IWorkersManager>()

    override suspend fun load(query: String, offset: Int, limit: Int): LoaderResult<List<WorkerDto>> {
        if (offset % limit != 0) return LoaderResult(ArrayList())

        val result = workerManager.getWorkers(
            coinProvider.getLabel(),
            (offset / params.perPage) + 1,
            params.perPage,
            params.status
        )

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}