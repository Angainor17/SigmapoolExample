package com.sigmapool.app.screens.miningProfit

import com.sigmapool.app.screens.miningProfit.params.MinerListParams
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.managers.IMinerManager
import com.sigmapool.common.models.MinerDto

const val MINER_PAGE_SIZE = 20

class MinerLoader(
    private val params: MinerListParams,
    private val manager: IMinerManager
) : IItemsLoader<MinerDto> {

    override suspend fun load(query: String, offset: Int, limit: Int): LoaderResult<List<MinerDto>> {
        if (offset % limit != 0) {
            return LoaderResult(ArrayList())
        }

        var pageSize = MINER_PAGE_SIZE     //TODO implement get data
        if (params.maxCount > 0) {
            pageSize = params.maxCount
        }

        val result = manager.getMiner((offset / pageSize) + 1, pageSize)

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}