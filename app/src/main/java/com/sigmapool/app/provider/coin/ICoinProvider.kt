package com.sigmapool.app.provider.coin

interface ICoinProvider {

    fun getLabel(): String

    fun addChangeListener(listener: (String) -> Unit)

}