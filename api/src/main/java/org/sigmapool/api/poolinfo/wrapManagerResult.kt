package org.sigmapool.api.poolinfo

import org.sigmapool.common.models.ManagerResult

suspend fun <T> wrapManagerResult(getter: suspend () -> T): ManagerResult<T> {
    return try {
        ManagerResult(getter())
    } catch (e: Throwable) {
        ManagerResult(error = e.message)
        // TODO: there is a lot of Throwables that we can handle different ways
    }
}