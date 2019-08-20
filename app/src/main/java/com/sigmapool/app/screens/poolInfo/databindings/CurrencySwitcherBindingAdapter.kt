package com.sigmapool.app.screens.poolInfo.databindings

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager

@BindingAdapter("android:set_selected")
    fun set_selected(view: View, selected: Boolean) {
        view.isSelected = selected
    }

    @BindingAdapter("android:btc_selected")
    fun setBtcSelected(pager: ViewPager, btcSelected: Boolean){
        pager.currentItem = if(btcSelected) 0 else 1
    }
