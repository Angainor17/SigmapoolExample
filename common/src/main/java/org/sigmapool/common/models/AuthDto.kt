package org.sigmapool.common.models

data class AuthDto(

    val id: String,

    val username: String,

    val email: String,

    var accessToken: String

)