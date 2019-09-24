package com.sigmapool.app.screens.chart.databindings

import androidx.databinding.BindingAdapter
import com.sigmapool.app.utils.customViews.chart.SingleChartView
import java.util.*

@BindingAdapter("app:xyValues", "app:dates")
fun xyValues(view: SingleChartView, values: FloatArray?, dates: List<Date>?) {
    if (values == null || dates == null) {
        return
    }
    view.createChart(values, dates)
    view.invalidate()
}
