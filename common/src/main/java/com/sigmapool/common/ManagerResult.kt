package com.sigmapool.common

class ManagerResult<T> (val data:T? = null, val error:String? = null){
    val success = error.isNullOrEmpty()
}