package com.sigmapool.common.listLibrary.decorators

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class SimpleDividerItemDecoration(context: Context): RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = Color.LTGRAY
        paint.strokeWidth = 1f;
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin

            c.drawLine(
                left.toFloat(),
                top.toFloat(),
                right.toFloat(),
                top.toFloat(),
                paint
            )
        }
    }
}