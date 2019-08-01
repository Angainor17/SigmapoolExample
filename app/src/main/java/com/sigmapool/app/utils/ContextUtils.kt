package com.sigmapool.app.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.sigmapool.app.App
import org.kodein.di.direct
import org.kodein.di.generic.instance

fun getString(@StringRes res: Int) = context().resources.getString(res)

fun getColor(@ColorRes res: Int) = context().resources.getColor(res)

private fun context(): Context = App.kodein.direct.instance()