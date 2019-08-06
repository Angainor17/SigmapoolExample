package com.sigmapool.api


private const val TEST_BASE_URL = "indimining.ru"
private const val PROD_BASE_URL = "sigmapool.com"

private const val isTestMode = true

val BASE_URL = if (isTestMode) TEST_BASE_URL else PROD_BASE_URL
