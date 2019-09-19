package com.sigmapool.common.utils

import java.text.DecimalFormat

object BigNumberHelper {
    private val postfixes = arrayOf("k", "M", "B", "T")
    fun format(floatNumber: Float): String {
        val number = floatNumber.toLong()
        val numberStr = number.toString() + ""
        val df = DecimalFormat()
        df.applyPattern("0.#E00")
        return if (numberStr.length > postfixes.size * 3 + 1) {
            df.format(floatNumber.toDouble()).toLowerCase()
        } else {
            String.format(
                "%.1f%s",
                number as Double / Math.pow(10.0, (numberStr.length - 1).toDouble()),
                postfixes[(numberStr.length - 1) / 3]
            )
        }
    }
}