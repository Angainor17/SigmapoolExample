package org.sigmapool.common.viewModels

interface IIndicatorSeekBarViewModel : OnValueChange<Float> {

    /*** Scale value from */
    fun getStartRange(): Int

    /*** Scale value to */
    fun getEndRange(): Int

    /*** Init value, when view init first*/
    fun getInitValue(): Int

    /*** Scale tick size*/
    fun getStep(): Int

    /*** Scale tick names */
    fun getArrayRes(): Int

    /*** Value displayed below seekBar*/
    fun getDisplayedValue(): CharSequence

}