package org.sigmapool.common.viewModels

import androidx.lifecycle.LiveData

interface ITitleViewModel {

    fun getTitle(): LiveData<String>

}