package org.sigmapool.sigmapool.screens.miningProfit

import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.loader.LoaderResult
import org.sigmapool.common.managers.IMinerManager
import org.sigmapool.common.models.MinerDto
import org.sigmapool.sigmapool.screens.miningProfit.params.MinerListParams


class MinerLoader(
    private val params: MinerListParams,
    private val manager: IMinerManager
) : IItemsLoader<MinerDto> {

    override suspend fun load(query: String, offset: Int, limit: Int): LoaderResult<List<MinerDto>> {
        if (offset % limit != 0) {
            return LoaderResult(ArrayList())
        }

        val result = manager.getMiner((offset / params.pageSize) + 1, params.pageSize)

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}