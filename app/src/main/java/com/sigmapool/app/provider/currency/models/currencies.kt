package com.sigmapool.app.provider.currency.models

import android.text.Html
import com.sigmapool.app.R
import com.sigmapool.app.screens.home.coin.BTC
import com.sigmapool.app.screens.home.coin.LTC
import com.sigmapool.common.models.CurrencyDto

val rubCurrency = AppCurrency(
    R.string.rub,
    Html.fromHtml(" &#x20bd").toString(),
    CurrencyParams(
        1,
        6,
        1,
        R.array.array_first_and_last_1_6,
        1,
        1
    )
)

val usdCurrency = AppCurrency(
    R.string.usd,
    "$",
    CurrencyParams(
        1,
        12,
        1,
        R.array.array_first_and_last_1_12,
        0,
        2
    )
)

val currencies = mutableMapOf(
    BTC to CurrencyDto(),
    LTC to CurrencyDto()
)