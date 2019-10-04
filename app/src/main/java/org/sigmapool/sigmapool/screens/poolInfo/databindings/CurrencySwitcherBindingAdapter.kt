package org.sigmapool.sigmapool.screens.poolInfo.databindings

import android.view.View
import androidx.databinding.BindingAdapter
import org.sigmapool.sigmapool.utils.customViews.viewPager.SwipeFreeViewPager

@BindingAdapter("android:set_selected")
    fun setSelected(view: View, selected: Boolean) {
        view.isSelected = selected
    }

    @BindingAdapter("android:btc_selected")
    fun setBtcSelected(pager: SwipeFreeViewPager, btcSelected: Boolean){
        pager.currentItem = if(btcSelected) 0 else 1
    }
