package org.sigmapool.api.retrofit

import okhttp3.Response

/*** Добавление к запросу Header с заданными параметрами*/
abstract class HeaderMapper {

    /*** Название самого параметра Header */
    abstract fun getHeaderName(): String

    /*** Валидно ли значение Header и можно ли его добавлять в запрос */
    fun isValueValid(): Boolean = !getValue().isNullOrEmpty()

    abstract fun getValue(): String

    open fun responseProceed(response: Response) = Unit
}