package com.badmitry.domain.entities

import java.io.Serializable

data class AuthData(
    val token: AuthToken,
    val user: User
) : Serializable