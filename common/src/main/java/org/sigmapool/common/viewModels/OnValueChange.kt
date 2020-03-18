package org.sigmapool.common.viewModels

interface OnValueChange<Type> {
    fun onChange(value: Type)
}