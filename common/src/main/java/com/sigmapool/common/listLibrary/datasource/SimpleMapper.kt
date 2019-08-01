package com.sigmapool.common.listLibrary.datasource

abstract class SimpleMapper<In, Out>{

    open fun map(input: List<In>): List<Out> {
        return input.map(::map)
    }

    abstract fun map(input:In): Out
}
