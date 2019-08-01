package com.sigmapool.common.listLibrary.viewmodel

interface BaseItemViewModel{

    val itemViewType:Int

    fun areItemsTheSame(item: BaseItemViewModel):Boolean

    fun areContentsTheSame(item: BaseItemViewModel):Boolean
}