package org.sigmapool.sigmapool.provider.lang

import androidx.annotation.StringRes

data class LocaleItem(
    @StringRes var labelResId: Int,
    val locale: String
)