package com.sigmapool.app.utils.customViews.viewPager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

open class FragmentViewPager : ViewPager {

    var isPagingEnabled = false
    var isWrapHeightEnabled = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if(!isWrapHeightEnabled) return

        val adapter = adapter as FragmentPagerAdapter
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