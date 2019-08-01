package com.sigmapool.common.listLibrary.loader

class LoaderResult<T>(val data: T? = null, val error: String? = null) {
    val isSuccess = error.isNullOrEmpty()
}