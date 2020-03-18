package org.sigmapool.sigmapool.screens.workers.params

const val ANY_STATUS = "any"
const val ONLINE_STATUS = "online"
const val OFFLINE_STATUS = "offline"

class WorkerListParams(
    val perPage: Int = 20,
    val status: String
)