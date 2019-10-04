package org.sigmapool.sigmapool.utils.interfaces

import org.sigmapool.common.listLibrary.loader.ItemsLoaderState
import org.sigmapool.common.listLibrary.loader.ItemsLoaderState.*
import org.sigmapool.sigmapool.utils.interfaces.ViewState.*

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