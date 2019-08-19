package com.sigmapool.common.viewModels

import androidx.lifecycle.LiveData

interface IIndicatorSeekBarViewModel : OnValueChange<Float> {

    /*** Scale value from */
    fun getStartRange(): LiveData<Int>

    /*** Scale value to */
    fun getEndRange(): LiveData<Int>

    /*** Init value, when view init first*/
    fun getInitValue(): LiveData<Int>

    /*** Scale tick size*/
    fun getStep(): LiveData<Int>

    /*** Scale tick names */
    fun getArrayRes(): LiveData<Int>

    /*** Value displayed below seekBar*/
    fun getDisplayedValue(): LiveData<CharSequence>

}