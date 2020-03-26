package org.sigmapool.common.databinding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

@BindingAdapter("app:isb_max")
fun setSeekBarMaxRange(view: IndicatorSeekBar, liveData: Float?) {
    liveData?.let {
        view.max = it
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
        val array = view.context.resources.getStringArray(it)
        view.tickCount = array.size
        view.customTickTexts(array)
    }
}

@BindingAdapter("app:changeListener", "app:isb_min", requireAll = true)
fun setSeekBarChangeListener(
    view: IndicatorSeekBar,
    action: OnSeekValueChangeListener?,
    value: Float?
) {
    view.indicator.contentView.visibility = View.GONE
    view.indicator.topContentView.visibility = View.GONE

    view.onSeekChangeListener = object : OnSeekChangeListener {
        override fun onSeeking(seekParams: SeekParams?) {
            seekParams?.progressFloat?.let { action?.onChange(it) }
        }

        override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) = Unit

        override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) = Unit
    }

    value?.let {
        view.min = it
        view.setProgress(it)
        action?.onChange(it)
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
