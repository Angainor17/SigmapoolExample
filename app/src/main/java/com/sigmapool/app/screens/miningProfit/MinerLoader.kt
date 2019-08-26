package com.sigmapool.app.screens.miningProfit

import com.sigmapool.app.screens.miningProfit.params.MinerListParams
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.models.MinerDto


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