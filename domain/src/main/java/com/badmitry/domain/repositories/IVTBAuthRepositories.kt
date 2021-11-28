package com.badmitry.domain.repositories

import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthToken
import io.reactivex.Single

interface IVTBAuthRepositories {
    fun getToken(authCredentials: AuthCredentials): Single<AuthToken>
}