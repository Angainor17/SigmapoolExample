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

    private val workerManager by kodein.instance<IWorkersManager>()

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