package com.sigmapool.app.utils

import androidx.lifecycle.LiveData

interface StateVM {
    val viewState: LiveData<ViewState>
}