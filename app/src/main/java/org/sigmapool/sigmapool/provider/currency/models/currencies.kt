package org.sigmapool.sigmapool.provider.currency.models

import android.text.Html
import org.sigmapool.common.models.CurrencyDto
import org.sigmapool.sigmapool.R

const val RUB_CODE = "rub"
const val USD_CODE = "usd"

val rubCurrency = AppCurrency(
    R.string.rub,
    Html.fromHtml(" &#x20bd").toString(),
    RUB_CODE,
    CurrencyParams(
        1f,
        6f,
        1,
        R.array.array_first_and_last_1_6,
        1,
        1
    )
)

val usdCurrency = AppCurrency(
    R.string.usd,
    "$",
    USD_CODE,
    CurrencyParams(
        1f,
        12f,
        1,
        R.array.array_first_and_last_1_12,
        0,
        2
    )
)

val currencies = mutableMapOf<String, CurrencyDto>()