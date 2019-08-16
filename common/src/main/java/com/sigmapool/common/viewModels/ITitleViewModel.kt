package com.sigmapool.common.viewModels

import androidx.lifecycle.LiveData

interface ITitleViewModel {

    val title1:String
    fun getTitle(): LiveData<String>

}