package com.sigmapool.common.viewModels

interface OnValueChange<Type> {
    fun onChange(value: Type)
}