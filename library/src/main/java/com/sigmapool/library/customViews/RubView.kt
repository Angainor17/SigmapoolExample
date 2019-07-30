package com.sigmapool.library.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.sigmapool.library.R

class RubView : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflate(context, R.layout.ic_rub, this)
    }
}