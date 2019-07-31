package com.sigmapool.common.viewModels

import androidx.lifecycle.LiveData

/**
 * TODO
 */
interface IIndicatorSeekBarViewModel {

    fun getStartRange(): LiveData<Int>

    fun getEndRange(): LiveData<Int>

    fun getCount(): LiveData<Int>

    fun getInitValue(): LiveData<Int>

    fun getStep(): LiveData<Int>

    fun getArrayRes(): LiveData<Int>

}