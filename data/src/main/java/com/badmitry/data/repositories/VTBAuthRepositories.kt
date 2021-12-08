package com.badmitry.data.repositories

import com.badmitry.data.api.VTBAuthApi
import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthToken
import com.badmitry.domain.entities.TokenFailed
import com.badmitry.domain.entities.User
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import com.badmitry.domain.entities.vtbcreditrequest.VtbCreditRequest
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

    override fun checkToken(token: String): Single<TokenFailed> {
        return api.checkToken(token)
    }

    override fun getUser(token: String): Single<User> {
        return api.getUser(token)
    }

    override fun creditApplication(
        token: String,
        vtbCreditRequest: VtbCreditRequest
    ): Single<VtbApplicationId> {
        return api.creditApplication(token, vtbCreditRequest)
    }
}