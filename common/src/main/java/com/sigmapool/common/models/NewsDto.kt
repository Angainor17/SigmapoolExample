package com.sigmapool.common.models

import java.util.*

data class NewsDto(
    val title: String,
    val brief: String,
    val url: String,
    val publishedAt: Date
)