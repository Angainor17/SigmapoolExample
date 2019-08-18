package com.sigmapool.app.screens.poolInfo.databindings

import android.view.View
import androidx.databinding.BindingAdapter

    @BindingAdapter("android:set_selected")
    fun set_selected(view: View, selected: Boolean) {
        view.isSelected = selected
    }
