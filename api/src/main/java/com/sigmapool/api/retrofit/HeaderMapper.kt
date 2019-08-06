package com.sigmapool.api.retrofit

/*** Добавление к запросу Header с заданными параметрами*/
abstract class HeaderMapper {

    /*** Название самого параметра Header */
    abstract fun getHeaderName(): String

    /*** Валидно ли значение Header и можно ли его добавлять в запрос */
    fun isValueValid(): Boolean = !getValue().isNullOrEmpty()

    abstract fun getValue(): String
}