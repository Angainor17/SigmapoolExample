package com.sigmapool.api.coin

internal interface ICoinService {

    suspend fun getCoin(): CoinInfo
}

