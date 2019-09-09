package com.sigmapool.app.provider.lang

import androidx.annotation.StringRes

data class LocaleItem(
    @StringRes val labelResId: Int,
    val locale: String
)