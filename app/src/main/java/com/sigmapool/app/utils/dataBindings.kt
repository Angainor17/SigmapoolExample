package com.sigmapool.app.utils

import android.widget.ImageView
import android.widget.RadioButton
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sigmapool.app.screens.bottomSheetScreen.ViewPagerAdapter
import com.sigmapool.app.utils.slider.MainSliderAdapter
import com.sigmapool.common.models.BlogDto
import ss.com.bannerslider.Slider
import ss.com.bannerslider.event.OnSlideClickListener

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

@BindingAdapter("app:src")
fun imageMipmapSrc(view: ImageView, mipmapRes: Int) {
    view.setImageResource(mipmapRes)
}

@BindingAdapter("app:setImages", "app:onImageClick", requireAll = true)
fun setImages(slider: Slider, items: ArrayList<BlogDto>?, listener: OnSlideClickListener?) {
    items.let {
        val adapter = it?.let { it1 -> MainSliderAdapter(it1) }
        slider.setAdapter(adapter)
        slider.setOnSlideClickListener(listener)
    }
}
