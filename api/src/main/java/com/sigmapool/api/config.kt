package com.sigmapool.api

// TODO:  favours though project structure
//private const val TEST_BASE_URL = "indimining.ru"
private const val TEST_BASE_URL = "proofpool.com"
private const val PROD_BASE_URL = "sigmapool.com"

private const val isTestMode = true

val BASE_URL = if (isTestMode) TEST_BASE_URL else PROD_BASE_URL
