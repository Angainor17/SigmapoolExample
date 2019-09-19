package com.sigmapool.app.screens.chart.databindings

import androidx.databinding.BindingAdapter
import com.sigmapool.app.utils.customViews.chart.ChartView

@BindingAdapter("app:xyValues")
    fun xyValues(view: ChartView, values:FloatArray? ) {
        if(values==null) {
            return
        }
            view.createChart(values)
            view.invalidate()
    }
