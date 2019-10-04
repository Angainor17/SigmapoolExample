package org.sigmapool.sigmapool.screens.earnings

import org.sigmapool.common.listLibrary.loader.IItemsLoader
import org.sigmapool.common.listLibrary.loader.LoaderResult
import org.sigmapool.common.managers.IEarningsManager
import org.sigmapool.common.models.PaymentItemDto
import org.sigmapool.sigmapool.screens.earnings.params.EarningsListParams

class EarningsLoader(
    private val params: EarningsListParams,
    private val manager: IEarningsManager
) : IItemsLoader<PaymentItemDto> {

    override suspend fun load(
        query: String,
        offset: Int,
        limit: Int
    ): LoaderResult<List<PaymentItemDto>> {
        if (offset % limit != 0) {
            return LoaderResult(ArrayList())
        }

        val result = manager.payments(params.coin, (offset / params.pageSize) + 1)

        return if (result.success) {
            LoaderResult(result.data)
        } else {
            LoaderResult(error = result.error)
        }
    }
}