package com.sigmapool.common.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

@BindingAdapter("app:isb_min")
fun setSeekBarMinRange(view: IndicatorSeekBar, liveData: LiveData<Int>) {
    liveData.value?.let {
        view.min = it.toFloat()

    }
}

@BindingAdapter("app:isb_max")
fun setSeekBarMaxRange(view: IndicatorSeekBar, liveData: LiveData<Int>) {
    liveData.value?.let {
        view.max = it.toFloat()
    }
}

@BindingAdapter("app:isb_step")
fun setSeekBarStep(view: IndicatorSeekBar, liveData: LiveData<Int>) {
    liveData.value?.let {
        view.setDecimalScale(it)
    }
}

@BindingAdapter("app:isb_tick_texts_array")
fun setSeekBarStringArray(view: IndicatorSeekBar, liveData: LiveData<Int>) {
    liveData.value?.let {
        view.customTickTexts(view.context.resources.getStringArray(it))
    }
}

@BindingAdapter("app:changeListener")
fun setSeekBarChangeListener(view: IndicatorSeekBar, action: OnSeekValueChangeListener) {
    view.onSeekChangeListener = object : OnSeekChangeListener {
        override fun onSeeking(seekParams: SeekParams?) {
            seekParams?.progressFloat?.let { action.onChange(it) }
        }

        override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

        }
    }
}

@BindingAdapter("app:iconChecked", "app:iconUnchecked", requireAll = true)
fun setImageViewIconSelectable(view: ImageView, iconChecked: Drawable, iconUnchecked: Drawable) {
    var isChecked = true
    view.setImageDrawable(iconChecked)
    view.setOnClickListener {
        isChecked = !isChecked
        view.setImageDrawable(if (isChecked) iconChecked else iconUnchecked)
    }
}

interface OnSeekValueChangeListener {
    fun onChange(float: Float)
}
