package org.sigmapool.api.models

internal class MinerListResponse : PagedListResponse() {
    val miners: ArrayList<Miner> = ArrayList()
}