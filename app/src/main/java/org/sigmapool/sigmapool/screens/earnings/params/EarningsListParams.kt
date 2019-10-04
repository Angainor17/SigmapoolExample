package org.sigmapool.sigmapool.screens.earnings.params

const val EARNINGS_PAGE_SIZE = 15

class EarningsListParams(
    var coin: String = "",
    val pageSize: Int = EARNINGS_PAGE_SIZE
)