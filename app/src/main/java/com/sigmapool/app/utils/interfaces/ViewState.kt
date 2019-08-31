package com.sigmapool.app.utils.interfaces

import com.sigmapool.app.utils.interfaces.ViewState.*
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState
import com.sigmapool.common.listLibrary.loader.ItemsLoaderState.*

enum class ViewState {
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