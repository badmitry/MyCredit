package com.badmitry.domain.repositories

import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthToken
import com.badmitry.domain.entities.TokenFailed
import com.badmitry.domain.entities.User
import io.reactivex.Single

interface IVTBAuthRepositories {
    fun getToken(authCredentials: AuthCredentials): Single<AuthToken>
    fun checkToken(token: String): Single<TokenFailed>
    fun getUser(token: String): Single<User>
}