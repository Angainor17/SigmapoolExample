package com.sigmapool.app.utils

import android.widget.RadioButton
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("onCheckedInitValue")
fun radioBtnOnClick(view: RadioButton, value: Boolean) {
    var isChecked = value
    view.setOnClickListener {
        isChecked = !isChecked
        view.isChecked = isChecked
    }
}

@BindingAdapter("app:onNavigationItemSelected")
fun onNavigationItemSelected(
    view: BottomNavigationView,
    listener: BottomNavigationView.OnNavigationItemSelectedListener
) {
    view.setOnNavigationItemSelectedListener(listener)
}

