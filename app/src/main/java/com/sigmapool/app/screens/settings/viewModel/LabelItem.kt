package com.sigmapool.app.screens.settings.viewModel

import androidx.annotation.StringRes
import com.sigmapool.app.provider.res.IResProvider


abstract class LabelItem (
    res: IResProvider,
    @StringRes labelResId: Int,
    val label: String = res.getString(labelResId)
)
