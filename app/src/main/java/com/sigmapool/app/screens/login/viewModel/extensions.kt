package com.sigmapool.app.screens.login.viewModel

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:setSelected1")
        fun setSelected1(view: View, selected: Boolean){
            view.isSelected = selected
}