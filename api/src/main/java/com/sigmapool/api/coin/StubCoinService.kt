package com.sigmapool.api.coin

import kotlinx.coroutines.delay
import retrofit2.Retrofit

internal class StubCoinService(retrofit: Retrofit) : ICoinService {

    override suspend fun getCoin(): CoinInfo {
        delay(2000)
        return CoinInfo(11171.00f)
    }
}