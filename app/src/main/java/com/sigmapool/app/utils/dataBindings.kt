package com.sigmapool.app.utils

import android.widget.RadioButton
import androidx.databinding.BindingAdapter

@BindingAdapter("onCheckedInitValue")
fun radioBtnOnClick(view: RadioButton, value: Boolean) {
    var isChecked = value
    view.setOnClickListener {
        isChecked = !isChecked
        view.isChecked = isChecked
    }
}
