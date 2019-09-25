package com.sigmapool.app.screens.chart.databindings

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.sigmapool.app.R
import com.sigmapool.app.utils.customViews.chart.MyMarkerView
import com.sigmapool.common.models.SeriesDto
import com.sigmapool.common.utils.INT_PATTERN
import com.sigmapool.common.utils.formatLongValue
import java.util.*

@BindingAdapter("app:chartData")
fun chartData(chart: LineChart, chartData: List<SeriesDto>) {
    val context = chart.context

    chart.setBackgroundColor(Color.TRANSPARENT)
    chart.description.isEnabled = false
    chart.isDoubleTapToZoomEnabled = false
    chart.setTouchEnabled(true)
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
    xAxis.textColor = Color.TRANSPARENT
    xAxis.isEnabled = true
    xAxis.isGranularityEnabled = true
    xAxis.granularity = 1f
    xAxis.setDrawAxisLine(false)

    chart.axisRight.isEnabled = false

    val yAxis: YAxis = chart.axisLeft
    yAxis.setDrawZeroLine(false)
    yAxis.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String =
            formatLongValue(value.toLong(), INT_PATTERN).toLowerCase()
    }
    yAxis.setDrawAxisLine(false)
    yAxis.textColor = axisTextColor

    yAxis.labelCount = 4
    yAxis.isGranularityEnabled = true
    yAxis.granularity = 1f
    yAxis.axisMaximum = getYAxisMax(chartData)
    yAxis.axisMinimum = getYAxisMin(chartData)

    chart.legend.isEnabled = false

    setData(chart, chartData)
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

fun setData(chart: LineChart, data: List<SeriesDto>) {
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

        refreshRange(chart)

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

        val drawable = ContextCompat.getDrawable(context, R.drawable.fade_white)
        lineDataSet.fillDrawable = drawable

        lineDataSet.setDrawHorizontalHighlightIndicator(false)
        lineDataSet.setDrawVerticalHighlightIndicator(true)
        lineDataSet.highLightColor = Color.WHITE

        chart.data = LineData(arrayListOf<ILineDataSet>(lineDataSet))
    }
}

private fun refreshRange(chart: LineChart) {
    chart.setVisibleXRangeMaximum(10f)
    chart.moveViewToX(0f)
}


