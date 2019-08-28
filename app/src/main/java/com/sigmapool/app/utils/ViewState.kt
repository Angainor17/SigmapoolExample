package com.sigmapool.app.utils

import com.sigmapool.app.utils.ViewState.*
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState.*

enum class ViewState(val text: String = "") {
    LOADING,
    ERROR,
    CONTENT;
}

fun ItemsLoaderState.mapToViwState(): ViewState = when (this) {
    Idle -> CONTENT
    Error -> ERROR
    Loading, Refreshing -> LOADING
    else -> LOADING
}