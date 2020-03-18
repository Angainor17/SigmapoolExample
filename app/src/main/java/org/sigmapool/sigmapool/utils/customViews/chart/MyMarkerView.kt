package org.sigmapool.sigmapool.utils.customViews.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import org.kodein.di.generic.instance
import org.sigmapool.common.models.SeriesDto
import org.sigmapool.common.utils.FLOAT_PATTERN
import org.sigmapool.common.utils.formatDateFull
import org.sigmapool.common.utils.formatLongValue
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider

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
