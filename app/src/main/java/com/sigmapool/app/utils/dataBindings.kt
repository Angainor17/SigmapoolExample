package com.sigmapool.app.utils

import android.widget.RadioButton
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sigmapool.app.screens.bottomSheetScreen.ViewPagerAdapter

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

@BindingAdapter("app:viewPagerAdapter")
fun viewPagerAdapter(view: FragmentViewPager, fragmentManager: FragmentManager) {
    view.adapter = ViewPagerAdapter(fragmentManager)
}

@BindingAdapter("app:onScreenChange")
fun onScreenChange(view: FragmentViewPager, position: Int) {
    view.currentItem = position
}



