package com.sigmapool.app

import com.sigmapool.app.utils.formatLongValue
import com.sigmapool.common.utils.FLOAT_PATTERN
import org.junit.Assert.assertEquals
import org.junit.Test

class StringUtilsTest {

    @Test
    fun formatLongValueTest() {
        assertEquals(
            "123M",
            formatLongValue(123456789L)
        )

        assertEquals(
            "987B",
            formatLongValue(987123456789L)
        )

        assertEquals(
            "5T",
            formatLongValue(4917123456789L)
        )

        assertEquals(
            "4.92T",
            formatLongValue(4917123456789L, FLOAT_PATTERN)
        )
    }
}
