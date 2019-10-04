package org.sigmapool.sigmapool.screens.workers

import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.loader.LoaderResult
import org.sigmapool.common.managers.IWorkersManager
import org.sigmapool.common.models.WorkerDto
import org.sigmapool.sigmapool.provider.coin.ICoinProvider
import org.sigmapool.sigmapool.screens.workers.params.WorkerListParams

class WorkerLoader(
    private val params: WorkerListParams,
    private val coinProvider: ICoinProvider,
    private val workerManager: IWorkersManager
) : IItemsLoader<WorkerDto> {

    var changeListener: ((ArrayList<WorkerDto>?) -> Unit)? = null

    val items = ArrayList<WorkerDto>()

    override suspend fun load(
        query: String,
        offset: Int,
        limit: Int
    ): LoaderResult<List<WorkerDto>> {
        if (offset % limit != 0) return LoaderResult(ArrayList())

        if (offset == 0) {
            items.clear()
        }

        val result = workerManager.getWorkers(
            coinProvider.getLabel().toLowerCase(),
            (offset / params.perPage) + 1,
            params.perPage,
            params.status
        )

        return if (result.success) {
            refreshFullList(result.data)
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }

    private fun refreshFullList(result: ArrayList<WorkerDto>?) {
        result.let {
            it?.forEach { items.add(it) }

            changeListener?.invoke(items)
        }
    }
}