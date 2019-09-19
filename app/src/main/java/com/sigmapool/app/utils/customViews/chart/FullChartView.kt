package com.sigmapool.app.views

//import android.databinding.BindingMethod;
//import android.databinding.BindingMethods;

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.lustgr.chart.*
import com.sigmapool.common.utils.BigNumberHelper
import java.util.*

/**
 * Created by stepan on 2017-07-08.
 */

class FullChartView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(
        context,
        attrs,
        defStyleAttr
    ) {
    private var th: TransformHelper? = null
    private var barTh: TransformHelper? = null
    private var compositeTh: TransformHelper? = null

    private var drawArea: DrawArea? = null
    private var barDrawArea: DrawArea? = null
    private var compositeDrawArea: DrawArea? = null

    private var candlesChart: Chart? = null
    private var barsChart: Chart? = null
    private var compositeChart: Chart? = null

    private var chartDates: List<Date>? = null

    private var bitmap: Bitmap? = null
    private var bitmapReady: Boolean = false

    fun createChart(chartLineValues: FloatArray, dates: List<Date>) {

        val linePaint = LinePaint(Color.WHITE, 2f)
        val chartLineModel = ChartLineModel("Test1", chartLineValues)
        val chartLine = ShadowedChartLine(linePaint, chartLineModel, 0x7fffffff, 0x00ffffff)
        val chart = Chart(chartLine.minimalChartArea, chartLine)

        val barChartLineIncreased = BooleanArray(chartLineValues.size / 2) { true }

        val barChartLinePaint = LinePaint(Color.GREEN, 2f)
        val barchartLineModel = BarChartLineModel(chartLineValues, barChartLineIncreased)
        val barChartLine = BarChartLine(barChartLinePaint, barchartLineModel)
        val barChart = Chart(barChartLine.minimalChartArea, barChartLine)
        setChart(chart, barChart, dates)
    }

    fun hideLine(lineName: String, hide: Boolean) {
        candlesChart!!.hideLine(lineName, hide)
        redrawBitmap()
    }

    fun setChart(chart: Chart, barChart: Chart, dates: List<Date>) {
        this.candlesChart = chart
        this.barsChart = barChart
        this.compositeChart = Chart(chart.chartArea)
        this.chartDates = dates
        updateChartsData()
    }


   override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec:Int){
    val wsize = MeasureSpec.getSize(widthMeasureSpec)
    val hsize = MeasureSpec.getSize(heightMeasureSpec)

    var hres=0
    var wres=0
    val whratio = 1.7737 // 878/495=1,7737 по фигме
    if ((hsize*whratio)>wsize) {
        wres = wsize
        hres = (wsize / whratio).toInt()
    }else {
        hres = hsize
        wres = (hsize * whratio).toInt()
    }
        setMeasuredDimension(wres, hres)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val hh = (h / 2).toFloat()
        val hhh = hh

        val rightMargin = 20f
        val leftMargin = 80f
        val bottomMargin = 40f

        this.drawArea = DrawArea(w.toFloat(), hhh, RectF(leftMargin, 20f, rightMargin, 0f))
        this.barDrawArea = DrawArea(w.toFloat(), h.toFloat(), RectF(leftMargin, 20 + hhh, rightMargin, bottomMargin))
        this.compositeDrawArea = DrawArea(w.toFloat(), h.toFloat(), RectF(20f, 10f, rightMargin, bottomMargin))

        if (bitmap != null) {
            bitmap!!.recycle()
            bitmap = null
        }

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444)
        updateChartsData()
    }

    private fun updateChartsData() {
        if (this.candlesChart != null && this.drawArea != null) {
            this.th = TransformHelper(this.candlesChart!!.chartArea, this.drawArea!!)
            this.barTh = TransformHelper(this.barsChart!!.chartArea, this.barDrawArea!!)
            this.compositeTh = TransformHelper(this.compositeChart!!.chartArea, this.compositeDrawArea!!)
            updateGridLines()
            redrawBitmap()
        }
    }

    private fun updateGridLines() {
        val ca = this.compositeTh!!.ca
        val lp = LinePaint(0x41ffffff, 1f)

        val verGridCount = 4
        val verGridStep = ca.w / verGridCount

        val dateLabels = DateLabelsHelper.createLabels(this.chartDates!!, verGridCount+1)

        var positionX = ca.minX
        val gridLines = mutableListOf<GridLine>()
        val barGridLines = mutableListOf<GridLine>()

        for (i in 0..verGridCount) {
            gridLines.add(OpaquedLabelVerticalGridLine(lp, positionX))
            if (i < verGridCount) {
                barGridLines.add(OpaquedLabelVerticalGridLine(lp, positionX, dateLabels[i]))
            }else {
                barGridLines.add(OpaquedLabelVerticalGridLine(lp, positionX))
            }
            positionX += verGridStep
        }

        val fullChartArea = candlesChart!!.chartArea
        val gridPositions = GridLineHelper.getGridLinesPositions(fullChartArea.minY, fullChartArea.maxY)

        if (gridPositions.size > 1) {
            var i = 0
            for (gridPosition in gridPositions) {
                if (i == gridPositions.size - 1) continue
                i++
                gridLines.add(OpaquedLabelHorizontalGridLine(lp, gridPosition, BigNumberHelper.format(gridPosition)))
                barGridLines.add(OpaquedLabelHorizontalGridLine(lp, gridPosition, BigNumberHelper.format(gridPosition)))
            }
        }

        candlesChart?.setGridLines(gridLines.toTypedArray())
        barsChart!!.setGridLines(barGridLines.toTypedArray())
    }

    override fun onDraw(canvas: Canvas) {
        if (bitmapReady) {
            canvas.drawBitmap(bitmap!!, 0f, 0f, null)
        }
    }

    private fun redrawBitmap() {
        if ( this.th != null && bitmap != null) {
            bitmap!!.eraseColor(Color.TRANSPARENT)
            val canvas = Canvas(bitmap!!)
            this.compositeChart!!.draw(canvas, this.compositeTh!!)
            this.candlesChart!!.draw(canvas, this.th!!)
            this.barsChart!!.draw(canvas, this.barTh!!)

            bitmapReady = true
            invalidate()
        }
    }
}



