package org.sigmapool.sigmapool.utils.interfaces

import androidx.lifecycle.LiveData

interface StateVM {
    val viewState: LiveData<ViewState>
}