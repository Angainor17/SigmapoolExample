package org.sigmapool.sigmapool.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T1, T2, R> liveDataZip(
    src1: LiveData<T1>,
    src2: LiveData<T2>,
    zipper: (T1, T2) -> R
): LiveData<R> {

    return MediatorLiveData<R>().apply {
        var src1Version = 0
        var src2Version = 0

        var lastSrc1: T1? = null
        var lastSrc2: T2? = null

        fun updateValueIfNeeded() {
            if (src1Version > 0 && src2Version > 0 &&
                lastSrc1 != null && lastSrc2 != null
            ) {
                value = zipper(lastSrc1!!, lastSrc2!!)
                src1Version = 0
                src2Version = 0
            }
        }

        addSource(src1) {
            lastSrc1 = it
            src1Version++
            updateValueIfNeeded()
        }

        addSource(src2) {
            lastSrc2 = it
            src2Version++
            updateValueIfNeeded()
        }
    }
}

fun <T1, T2, T3, R> liveDataZip(
    src1: LiveData<T1>,
    src2: LiveData<T2>,
    src3: LiveData<T3>,
    zipper: (T1, T2, T3) -> R
): LiveData<R> {

    return MediatorLiveData<R>().apply {
        var src1Version = 0
        var src2Version = 0
        var src3Version = 0

        var lastSrc1: T1? = null
        var lastSrc2: T2? = null
        var lastSrc3: T3? = null

        fun updateValueIfNeeded() {
            if (src1Version > 0 && src2Version > 0 && src3Version > 0 &&
                lastSrc1 != null && lastSrc2 != null && lastSrc3 != null
            ) {
                value = zipper(lastSrc1!!, lastSrc2!!, lastSrc3!!)
                src1Version = 0
                src2Version = 0
                src3Version = 0
            }
        }

        addSource(src1) {
            lastSrc1 = it
            src1Version++
            updateValueIfNeeded()
        }

        addSource(src2) {
            lastSrc2 = it
            src2Version++
            updateValueIfNeeded()
        }

        addSource(src3) {
            lastSrc3 = it
            src3Version++
            updateValueIfNeeded()
        }
    }
}

fun <T, R> liveDataZip(
    liveItems: List<LiveData<T>>,
    zipper: (List<T>) -> R
): LiveData<R> {

    return MediatorLiveData<R>().apply {
        var srcVersions = MutableList(liveItems.size) { 0 }
        val lastSrcs = MutableList<T?>(liveItems.size) { null }

        fun updateValueIfNeeded() {
            if (srcVersions.all { it > 0 } && lastSrcs.all { it != null }) {
                val arrayList = ArrayList<LiveData<T>>()
                repeat(liveItems.size) { arrayList.addAll(liveItems) }
                value = zipper(arrayList as List<T>)

                srcVersions = MutableList(liveItems.size) { 0 }
            }
        }

        liveItems.forEachIndexed { index, item ->
            addSource(item) {
                lastSrcs[index] = it
                srcVersions[index]++
                updateValueIfNeeded()
            }
        }
    }
}