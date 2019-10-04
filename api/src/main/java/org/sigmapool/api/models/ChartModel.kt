package org.sigmapool.api.models

data class ChartModel(
    val xyValues: Array<SeriesModel>
)

data class SeriesModel(
    val time:String,
    val hashrate:Long
)