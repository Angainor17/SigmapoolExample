package com.sigmapool.app.utils

import android.text.SpannableString
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import com.sigmapool.common.utils.INT_PATTERN
import com.sigmapool.common.utils.format

fun formatLongValue(value: Long): String {
    var result = value
    val arr = arrayOf("", "K", "M", "B", "T", "P", "E")
    var index = 0
    while (result / 1000 >= 1) {
        result /= 1000
        index++
    }

    return String.format("%s%s", result.format(INT_PATTERN), arr[index])
}

fun spannableString(
    text: String,
    textSize: Int? = null,
    color: Int? = null,
    fontFamily: String? = null
): SpannableString {
    val ss1 = SpannableString(text)

    textSize?.let { ss1.setSpan(AbsoluteSizeSpan(it, true), 0, text.length, 0) }
    color?.let { ss1.setSpan(ForegroundColorSpan(it), 0, text.length, 0) }
    fontFamily?.let { ss1.setSpan(TypefaceSpan(it), 0, text.length, 0) }

    return ss1
}

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))

