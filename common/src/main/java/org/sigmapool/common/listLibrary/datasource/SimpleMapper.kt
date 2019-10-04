package org.sigmapool.common.listLibrary.datasource

abstract class SimpleMapper<In, Out>{

    open fun map(input: List<In>): List<Out> = input.map(::map)

    abstract fun map(input:In): Out
}
