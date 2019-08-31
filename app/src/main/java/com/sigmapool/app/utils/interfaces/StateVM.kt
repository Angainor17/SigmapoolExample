package com.sigmapool.app.utils.interfaces

import androidx.lifecycle.LiveData

interface StateVM {
    val viewState: LiveData<ViewState>
}