package com.sigmapool.app.screens.earnings

import android.util.Log
import com.google.gson.Gson
import com.sigmapool.app.screens.earnings.params.EarningsListParams
import com.sigmapool.common.listLibrary.loader.IItemsLoader
import com.sigmapool.common.listLibrary.loader.LoaderResult
import com.sigmapool.common.managers.IEarningsManager
import com.sigmapool.common.models.PaymentItemDto

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
        Log.d("voronin", "payments")
        Log.d("voronin", "params.coinValue " + params.coin)
        Log.d("voronin", "payments page = " + (offset / params.pageSize) + 1)

        return if (result.success) {
            Log.d("voronin", "" + Gson().toJson(result.data))
            LoaderResult(result.data)
        } else {
            Log.d("voronin", result.error)
            LoaderResult(error = result.error)
        }
    }
}