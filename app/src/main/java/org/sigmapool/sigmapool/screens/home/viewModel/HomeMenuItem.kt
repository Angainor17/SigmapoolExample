package org.sigmapool.sigmapool.screens.home.viewModel

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel


class HomeMenuItem(
    @DrawableRes val imageRes: Int,
    @StringRes val textRes: Int,
    val onClickListener: View.OnClickListener
) : ViewModel()
