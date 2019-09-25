package com.sigmapool.app.utils.customViews.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.sigmapool.app.App.Companion.kodein
import com.sigmapool.app.R
import com.sigmapool.app.provider.res.IResProvider
import com.sigmapool.common.models.SeriesDto
import com.sigmapool.common.utils.FLOAT_PATTERN
import com.sigmapool.common.utils.formatDateFull
import com.sigmapool.common.utils.formatLongValue
import org.kodein.di.generic.instance

@SuppressLint("ViewConstructor")
class MyMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    private val res by kodein.instance<IResProvider>()

    private val tvTime: TextView = findViewById(R.id.tvTime)
    private val tvHashrate: TextView = findViewById(R.id.tvHashrate)

    override fun refreshContent(entry: Entry?, highlight: Highlight?) {

        if (entry is Entry) {
            val y = entry.y
            val item = entry.data as SeriesDto

            tvTime.text = item.time.formatDateFull()

            val hashrateString = "" + formatLongValue(
                y.toLong(),
                FLOAT_PATTERN
            ) + res.getString(R.string.hashrate_per_second)

            tvHashrate.text = hashrateString
        }

        super.refreshContent(entry, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}
