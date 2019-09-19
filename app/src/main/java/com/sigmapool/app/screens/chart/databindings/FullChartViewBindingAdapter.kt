package com.sigmapool.app.screens.chart.databindings

import androidx.databinding.BindingAdapter
import com.sigmapool.app.views.FullChartView
import java.util.*

@BindingAdapter("app:xyValues", "app:dates")
    fun xyValues(view: FullChartView, values:FloatArray? , dates:List<Date>?) {
        if(values==null || dates == null) {
            return
        }
        view.createChart(values, dates)
        view.invalidate()
    }
