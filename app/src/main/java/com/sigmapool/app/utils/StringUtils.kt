package com.sigmapool.app.utils

import android.text.SpannableString
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan

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

