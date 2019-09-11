package com.sigmapool.common.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

@BindingAdapter("app:isb_max")
fun setSeekBarMaxRange(view: IndicatorSeekBar, liveData: Int?) {
    liveData?.let {
        view.max = it.toFloat()
    }
}

@BindingAdapter("app:isb_step")
fun setSeekBarStep(view: IndicatorSeekBar, liveData: Int?) {
    liveData?.let {
        view.setDecimalScale(it)
    }
}

@BindingAdapter("app:isb_tick_texts_array")
fun setSeekBarStringArray(view: IndicatorSeekBar, liveData: Int?) {
    liveData?.let {
        view.customTickTexts(view.context.resources.getStringArray(it))
    }
}

@BindingAdapter("app:changeListener", "app:isb_min", requireAll = true)
fun setSeekBarChangeListener(
    view: IndicatorSeekBar,
    action: OnSeekValueChangeListener?,
    value: Int?
) {
    view.onSeekChangeListener = object : OnSeekChangeListener {
        override fun onSeeking(seekParams: SeekParams?) {
            seekParams?.progressFloat?.let { action?.onChange(it) }
        }

        override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

        }
    }

    value?.let {
        view.min = it.toFloat()
        view.setProgress(it.toFloat())
        action?.onChange(it.toFloat())
    }
}

@BindingAdapter("app:iconChecked", "app:iconUnchecked", "app:onImageSelected", requireAll = true)
fun setImageViewIconSelectable(
    view: ImageView,
    iconChecked: Drawable,
    iconUnchecked: Drawable,
    onImageClick: OnImageClick
) {
    var isChecked = true
    view.setImageDrawable(iconChecked)
    view.setOnClickListener {
        isChecked = !isChecked
        view.setImageDrawable(if (isChecked) iconChecked else iconUnchecked)
        onImageClick.onCurrencyBtnSelected(isChecked)
    }
}

interface OnSeekValueChangeListener {
    fun onChange(float: Float)
}

interface OnImageClick {
    fun onCurrencyBtnSelected(isSelected: Boolean)
}
