package com.sigmapool.app.utils.databindings

import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.sigmapool.app.R
import com.sigmapool.app.utils.customViews.chart.MyMarkerView
import com.sigmapool.common.managers.PERIOD_DAY
import com.sigmapool.common.models.SeriesDto
import com.sigmapool.common.utils.*
import java.util.*

val axisTextColor = Color.parseColor("#B5FFFFFF")

@BindingAdapter("app:chartData", "app:chartMode")
fun chartData(chart: BarChart, chartData: List<SeriesDto>, chartMode: String) {
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
    xAxis.textColor = axisTextColor
    xAxis.isEnabled = true
    xAxis.axisLineColor = Color.TRANSPARENT
    xAxis.isGranularityEnabled = true
    xAxis.granularity = 1f
    xAxis.labelCount = 5
    xAxis.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return xAxisFormatter(value, chartMode, chartData)
        }
    }
    xAxis.position = XAxis.XAxisPosition.BOTTOM

    chart.axisRight.isEnabled = false

    val yAxis: YAxis = chart.axisLeft
    yAxis.setDrawZeroLine(false)
    yAxis.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float) = formatYAxis(value)
    }

    yAxis.textColor = axisTextColor
    yAxis.labelCount = 4
    yAxis.isGranularityEnabled = true
    yAxis.granularity = 1f
    yAxis.axisMaximum = getYAxisMax(chartData)
    yAxis.axisMinimum = -0.15f * getYAxisMax(chartData)
    yAxis.setDrawAxisLine(false)

    chart.legend.isEnabled = false

    setData(chart, chartData)
}

fun xAxisFormatter(value: Float, chartMode: String, chartData: List<SeriesDto>): String {
    try {
        val position = value.toInt()
        if (position >= chartData.size) return ""

        val itemDate = chartData[position].time
        if (chartMode == PERIOD_DAY) {
            return itemDate.formatDateShort()
        }
        return itemDate.formatTime()
    } catch (e: Exception) {
        return ""
    }
}

fun formatYAxis(value: Float): String {
    if (value == 0f) return "0"
    val newValue = (value / 1000000000000000f)
    if (newValue < 1f) return newValue.format(FLOAT_PATTERN)
    return newValue.format(INT_PATTERN) + "k"
}

private fun getYAxisMax(data: List<SeriesDto>?): Float {
    if (!data.isNullOrEmpty()) {
        val maxValue = data.map { it.hashrate }.max()!!
        return 1.2f * maxValue
    }
    return 22.5f
}

fun setData(chart: BarChart, data: List<SeriesDto>) {
    val values = ArrayList<BarEntry>()

    data.forEachIndexed { index, item ->
        values.add(
            BarEntry(
                index.toFloat(),
                item.hashrate,
                item
            )
        )
    }

    val barDataSet: BarDataSet

    if (chart.data != null && chart.data.dataSetCount > 0) {
        barDataSet = chart.data.getDataSetByIndex(0) as BarDataSet
        barDataSet.values = values
        barDataSet.notifyDataSetChanged()

        chart.data.notifyDataChanged()
        chart.animateX(500)
        chart.notifyDataSetChanged()

        refreshRange(chart)
    } else {
        barDataSet = BarDataSet(values, "")

        barDataSet.color = Color.parseColor("#35D4A2")
        barDataSet.setDrawValues(false)
        barDataSet.formLineWidth = 1f
        barDataSet.formSize = 15f
        barDataSet.valueTextSize = 9f
        barDataSet.highLightColor = Color.WHITE

        val barData = BarData(barDataSet)
        barData.setValueTextSize(10f)
        barData.barWidth = 0.45f
        chart.data = barData
    }
}

private fun refreshRange(chart: BarChart) {
    chart.setVisibleXRangeMaximum(X_RANGE_MAX)
    chart.moveViewToX(0f)
}

