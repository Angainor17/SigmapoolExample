package com.sigmapool.app.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.sigmapool.app.screens.bottomSheetScreen.ViewPagerAdapter
import com.sigmapool.app.screens.calculator.adapter.CalcTabAdapter
import com.sigmapool.app.screens.calculator.viewModel.CalcItemVM
import com.sigmapool.app.screens.home.adapter.CoinViewPagerAdapter
import com.sigmapool.app.screens.home.coin.CoinsVM
import com.sigmapool.app.utils.customViews.FragmentViewPager
import com.sigmapool.app.utils.slider.MainSliderAdapter
import com.sigmapool.common.models.BlogDto
import com.sigmapool.common.utils.px
import ss.com.bannerslider.Slider
import ss.com.bannerslider.event.OnSlideClickListener

@BindingAdapter("app:onNavigationItemSelected")
fun onNavigationItemSelected(
    view: BottomNavigationView,
    listener: BottomNavigationView.OnNavigationItemSelectedListener
) {
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("app:initCoinAdapter", "app:fragmentManager", requireAll = true)
fun initCoinAdapter(view: ViewPager, vm: CoinsVM, fragmentManager: FragmentManager) {
    view.pageMargin = 20.px
    view.adapter = CoinViewPagerAdapter(vm.getCoins(), fragmentManager)
}

@BindingAdapter("app:viewPagerAdapter")
fun viewPagerAdapter(view: FragmentViewPager, fragmentManager: FragmentManager) {
    view.adapter = ViewPagerAdapter(fragmentManager)
}

@BindingAdapter("app:initCalcAdapter", "app:viewCalcTabAdapter")
fun viewCalcTabAdapter(view: FragmentViewPager, items: ArrayList<CalcItemVM>, fragmentManager: FragmentManager) {
    view.adapter = CalcTabAdapter(items, fragmentManager)
}

@BindingAdapter("app:onScreenChange")
fun onScreenChange(view: FragmentViewPager, position: Int) {
    view.currentItem = position
}

@BindingAdapter("app:src")
fun imageMipmapSrc(view: ImageView, mipmapRes: Int) {
    view.setImageResource(mipmapRes)
}

@BindingAdapter("app:isPagingEnabled")
fun isPagingEnabled(view: FragmentViewPager, isPagingEnabled: Boolean) {
    view.isPagingEnabled = isPagingEnabled
}

@BindingAdapter("app:isWrapHeightEnabled")
fun isWrapHeightEnabled(view: FragmentViewPager, isWrapHeightEnabled: Boolean) {
    view.isWrapHeightEnabled = isWrapHeightEnabled
}

@BindingAdapter("bind:pager")
fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager) {
    view.setupWithViewPager(pagerView, true)
}

@BindingAdapter("app:setImages", "app:onImageClick", requireAll = true)
fun setImages(slider: Slider, items: ArrayList<BlogDto>?, listener: OnSlideClickListener?) {
    items.let {
        val adapter = it?.let { it1 -> MainSliderAdapter(it1) }
        slider.setAdapter(adapter)
        if (adapter != null) {
            slider.setInterval(10000)
        }
        slider.setOnSlideClickListener(listener)
    }
}

@BindingAdapter("app:onActivatedChange")
fun onActivatedChange(view: View, isActivated: Boolean) {
    view.isActivated = isActivated
}


@BindingAdapter("rotateOnClick")
fun rotateOnClick(view: View, listener: View.OnClickListener) {
    val animation = view.animation
    view.setOnClickListener {
        if (animation == null) {
            view.startAnimation(createAnimation())
        } else {
            animation.cancel()
            view.animation = null
        }
        listener.onClick(view)
    }
}

private fun createAnimation(): Animation {
    val anim = RotateAnimation(
        0f,
        360f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    anim.duration = 1400

    return anim
}
