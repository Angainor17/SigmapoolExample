package com.sigmapool.app.screens.miningProfit

import com.sigmapool.api.managers.MinerManager
import com.sigmapool.common.IMinerManager
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.models.MinerDto

class MinerLoader : IItemsLoader<MinerDto> {

    private val manager: IMinerManager = MinerManager()

    override suspend fun load(query: String, offset: Int, limit: Int): LoaderResult<List<MinerDto>> {
        val result = manager.getMiner(offset, limit)

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}