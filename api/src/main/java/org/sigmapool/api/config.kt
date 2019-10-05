package org.sigmapool.api


private const val TEST_BASE_URL = "proofpool.com"
private const val PROD_BASE_URL = "sigmapool.com"

private val isTestMode = false

val BASE_URL = if (isTestMode) TEST_BASE_URL else PROD_BASE_URL
