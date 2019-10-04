package org.sigmapool.sigmapool.utils.customViews

import androidx.lifecycle.MutableLiveData
import org.kodein.di.generic.instance
import org.sigmapool.sigmapool.App.Companion.kodein
import org.sigmapool.sigmapool.R
import org.sigmapool.sigmapool.provider.res.IResProvider

class CustomSwitchVm(
    val leftText: String = "",
    val rightText: String = "",
    textColorRes: Int = R.color.custom_switcher_text_color,
    leftBgDrawable: Int = R.drawable.custom_switcher_left_bg,
    private val rightBgDrawableSelected: Int = R.drawable.custom_switcher_bg,
    private val rightBgDrawableUnselected: Int = R.drawable.custom_switcher_bg_unselected
) {

    val res by kodein.instance<IResProvider>()

    var clickListener: OnSwitchSelected? = null

    val leftActivated = MutableLiveData(true)
    val rightActivated = MutableLiveData(false)

    var textColor = res.getColorStateList(textColorRes)
    var leftDrawable = MutableLiveData(res.getDrawable(leftBgDrawable))
    var rightDrawable = MutableLiveData(res.getDrawable(rightBgDrawableUnselected))

    fun rightClick() {
        if (!rightActivated.value!!) {
            leftActivated.postValue(false)
            rightActivated.postValue(true)
            rightDrawable.postValue(res.getDrawable(rightBgDrawableSelected))

            clickListener?.onSelected(false)
        }
    }

    fun leftClick() {
        if (!leftActivated.value!!) {
            rightActivated.postValue(false)
            leftActivated.postValue(true)
            rightDrawable.postValue(res.getDrawable(rightBgDrawableUnselected))

            clickListener?.onSelected(true)
        }
    }

    fun refreshBg() {
        rightDrawable.postValue(rightDrawable.value)
        leftActivated.postValue(leftActivated.value)
        rightActivated.postValue(rightActivated.value)
    }
}

interface OnSwitchSelected {
    fun onSelected(leftSelected: Boolean)
}