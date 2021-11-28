package com.badmitry.data.repositories

import com.badmitry.data.api.VTBAuthApi
import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthToken
import com.badmitry.domain.repositories.IVTBAuthRepositories
import io.reactivex.Single

class VTBAuthRepositories(private val api: VTBAuthApi) : IVTBAuthRepositories {
    override fun getToken(authCredentials: AuthCredentials): Single<AuthToken> {
        return api.auth(
            authCredentials.clientCredentials,
            authCredentials.clientId,
            authCredentials.clientSecret,
            authCredentials.host
        )
    }
}