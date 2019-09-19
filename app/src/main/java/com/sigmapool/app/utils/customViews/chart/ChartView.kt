package com.sigmapool.app.utils.customViews.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.lustgr.chart.*

class ChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    lateinit var chart:Chart
    lateinit var th:TransformHelper
    var b:Boolean = false

    override fun onDraw(canvas: Canvas) {
        if(!b) return
        chart.draw(canvas, th)
    }

    fun createChart(f:FloatArray){
        val linePaint = LinePaint(Color.WHITE, 3f)
        val chartLineModel = ChartLineModel("Test1", f)
        val offsets = RectF(chartLineModel.minimalChartArea.minX, chartLineModel.minimalChartArea.maxX, chartLineModel.minimalChartArea.minY, chartLineModel.minimalChartArea.maxY) // ChartArea запихивается в DrawArea
        val chartLine = ShadowedChartLine(linePaint, chartLineModel, 0xBFFFFFFF.toInt(), 0x00FFFFFF)
        chart = Chart(chartLineModel.minimalChartArea, chartLine)
        th = TransformHelper(chart.chartArea, DrawArea(width.toFloat(), height.toFloat(), offsets))
        b = true
    }
}
