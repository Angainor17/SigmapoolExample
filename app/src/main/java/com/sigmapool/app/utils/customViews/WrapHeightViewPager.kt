package com.sigmapool.app.utils.customViews

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import com.sigmapool.app.screens.home.adapter.CoinViewPagerAdapter

class WrapHeightViewPager : ViewPager {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val adapter = adapter as CoinViewPagerAdapter
        val currentView = adapter.getItem(0).view

        if (currentView != null) {
            currentView.measure(widthMeasureSpec, heightMeasureSpec)
            super.onMeasure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(currentView.measuredHeight, MeasureSpec.EXACTLY)
            )
            return
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}