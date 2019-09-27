package com.sigmapool.app.utils.customViews.chart

import android.view.MotionEvent
import com.github.mikephil.charting.listener.ChartTouchListener
import com.github.mikephil.charting.listener.OnChartGestureListener

open class AbstractOnChartGestureListener : OnChartGestureListener {

    override fun onChartGestureEnd(
        me: MotionEvent?,
        lastPerformedGesture: ChartTouchListener.ChartGesture?
    ) = Unit

    override fun onChartFling(
        me1: MotionEvent?,
        me2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ) = Unit

    override fun onChartSingleTapped(me: MotionEvent?) = Unit

    override fun onChartGestureStart(
        me: MotionEvent?,
        lastPerformedGesture: ChartTouchListener.ChartGesture?
    ) = Unit

    override fun onChartScale(me: MotionEvent?, scaleX: Float, scaleY: Float) = Unit

    override fun onChartLongPressed(me: MotionEvent?) = Unit

    override fun onChartDoubleTapped(me: MotionEvent?) = Unit

    override fun onChartTranslate(me: MotionEvent?, dX: Float, dY: Float) = Unit
}