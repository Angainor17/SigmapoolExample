package org.sigmapool.sigmapool.utils.databindings

import android.graphics.Color
import android.graphics.Matrix
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import org.sigmapool.common.managers.PERIOD_HOUR
import org.sigmapool.common.models.SeriesDto
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.utils.customViews.chart.AbstractOnChartGestureListener
import org.sigmapool.sigmapool.utils.customViews.chart.MyMarkerView
import java.util.*


private val AXIS_TEXT_COLOR = Color.parseColor("#828282")
const val X_RANGE_MAX = 15f

@BindingAdapter("app:homeChartData")
fun homeChartData(chart: LineChart, chartData: List<SeriesDto>) {
    lineChartCommonCustomize(chart, chartData)


    val xAxis: XAxis = chart.xAxis
    val leftAxis: YAxis = chart.axisLeft

    leftAxis.gridLineWidth = 0f
    leftAxis.gridColor = Color.TRANSPARENT
    leftAxis.textColor = AXIS_TEXT_COLOR

    xAxis.textColor = AXIS_TEXT_COLOR
    xAxis.setDrawAxisLine(false)
    xAxis.gridLineWidth = 0f
    xAxis.labelCount = 4
    xAxis.gridColor = Color.TRANSPARENT
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float) = xAxisFormatter(value, PERIOD_HOUR, chartData)
    }

    chart.setTouchEnabled(false)

    setData(chart, chartData, R.drawable.fade_violet, false)
}

private fun syncCharts(
    mainChart: ViewPortHandler,
    tempChart: ViewPortHandler,
    tempChartView: View
) {
    val mainMatrix: Matrix = mainChart.matrixTouch
    val mainVals = FloatArray(9)
    val otherMatrix: Matrix = tempChart.matrixTouch
    val otherVals = FloatArray(9)
    mainMatrix.getValues(mainVals)

    otherMatrix.getValues(otherVals)
    otherVals[Matrix.MSCALE_X] = mainVals[Matrix.MSCALE_X]
    otherVals[Matrix.MTRANS_X] = mainVals[Matrix.MTRANS_X]
    otherVals[Matrix.MSKEW_X] = mainVals[Matrix.MSKEW_X]
    otherMatrix.setValues(otherVals)
    tempChart.refresh(otherMatrix, tempChartView, true)

}

@BindingAdapter("app:chartData", "app:barChart")
fun chartData(chart: LineChart, chartData: List<SeriesDto>, barChart: BarChart) {
    lineChartCommonCustomize(chart, chartData)
    chart.setTouchEnabled(true)

    chart.onChartGestureListener = object : AbstractOnChartGestureListener() {
        override fun onChartTranslate(me: MotionEvent?, dX: Float, dY: Float) {
            syncCharts(chart.viewPortHandler, barChart.viewPortHandler, barChart)
            Log.d("voronin","chart onChartTranslate")
        }
    }

    barChart.onChartGestureListener = object : AbstractOnChartGestureListener() {
        override fun onChartTranslate(me: MotionEvent?, dX: Float, dY: Float) {
            Log.d("voronin","barChart onChartTranslate")
            syncCharts(barChart.viewPortHandler, chart.viewPortHandler, chart)
        }
    }

    val xAxis: XAxis = chart.xAxis

    xAxis.textColor = Color.TRANSPARENT
    xAxis.setDrawAxisLine(false)

    setData(chart, chartData, R.drawable.fade_white)
}

private fun lineChartCommonCustomize(chart: LineChart, chartData: List<SeriesDto>) {
    val context = chart.context

    chart.setBackgroundColor(Color.TRANSPARENT)
    chart.description.isEnabled = false
    chart.isDoubleTapToZoomEnabled = false

    chart.isDragEnabled = true
    chart.setDrawGridBackground(false)
    chart.setNoDataTextColor(Color.WHITE)
    chart.setDrawBorders(false)
    chart.setNoDataText(context.getString(R.string.chart_no_data))

    val markerView =
        MyMarkerView(context, R.layout.custom_marker_view)
    markerView.chartView = chart
    chart.marker = markerView

    val xAxis: XAxis = chart.xAxis
    xAxis.isEnabled = true
    xAxis.isGranularityEnabled = true
    xAxis.granularity = 1f

    chart.axisRight.isEnabled = false

    val yAxis: YAxis = chart.axisLeft
    yAxis.setDrawZeroLine(false)
    yAxis.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float) = formatYAxis(value)
    }
    yAxis.setDrawAxisLine(false)
    yAxis.textColor = axisTextColor

    yAxis.labelCount = 4
    yAxis.isGranularityEnabled = true
    yAxis.granularity = 1f
    yAxis.axisMaximum = getYAxisMax(chartData)
    yAxis.axisMinimum = getYAxisMin(chartData)

    chart.legend.isEnabled = false
}

private fun getYAxisMin(data: List<SeriesDto>?): Float {
    if (!data.isNullOrEmpty()) {
        val maxValue = data.map { it.hashrate }.max()!!
        return -0.2f * (1.2f * maxValue)
    }
    return -2.5f
}

private fun getYAxisMax(data: List<SeriesDto>?): Float {
    if (!data.isNullOrEmpty()) {
        val maxValue = data.map { it.hashrate }.max()!!
        return 1.1f * maxValue
    }
    return 22.5f
}

fun setData(
    chart: LineChart,
    data: List<SeriesDto>,
    gradient: Int,
    refreshRangeEnable: Boolean = true
) {
    val values = ArrayList<Entry>()

    data.forEachIndexed { index, item ->
        values.add(
            Entry(
                index.toFloat(),
                item.hashrate,
                item
            )
        )
    }

    val context = chart.context
    val lineDataSet: LineDataSet

    if (chart.data != null && chart.data.dataSetCount > 0) {
        lineDataSet = chart.data.getDataSetByIndex(0) as LineDataSet
        lineDataSet.values = values
        lineDataSet.notifyDataSetChanged()

        chart.data.notifyDataChanged()
        chart.animateX(500)
        chart.notifyDataSetChanged()

        if (refreshRangeEnable) {
            refreshRange(chart)
        }
    } else {
        lineDataSet = LineDataSet(values, "")

        lineDataSet.color = Color.WHITE
        lineDataSet.setCircleColor(Color.TRANSPARENT)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircleHole(false)
        lineDataSet.formLineWidth = 1f
        lineDataSet.formSize = 15f
        lineDataSet.valueTextSize = 9f
        lineDataSet.setDrawFilled(true)

        val drawable = ContextCompat.getDrawable(context, gradient)
        lineDataSet.fillDrawable = drawable

        lineDataSet.setDrawHorizontalHighlightIndicator(false)
        lineDataSet.setDrawVerticalHighlightIndicator(true)
        lineDataSet.highLightColor = Color.WHITE

        chart.data = LineData(arrayListOf<ILineDataSet>(lineDataSet))
    }
}

private fun refreshRange(chart: LineChart) {
    chart.setVisibleXRangeMaximum(X_RANGE_MAX)
    chart.moveViewToX(0f)
}


