package org.sigmapool.api.models

import java.util.*

internal data class NewsItem(
    val title: String,
    val brief: String,
    val url: String,
    val publishedAt: Date
)