package com.sigmapool.app.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan

fun spannableString(
    text: String,
    textSize: Int = 16,
    color: Int = Color.BLACK,
    fontFamily: String = "google_sans_regular"
): SpannableString {
    val ss1 = SpannableString(text)

    ss1.setSpan(AbsoluteSizeSpan(textSize, true), 0, text.length, 0)
    ss1.setSpan(ForegroundColorSpan(color), 0, text.length, 0)
    ss1.setSpan(TypefaceSpan(fontFamily), 0, text.length, 0)

    return ss1
}

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))

