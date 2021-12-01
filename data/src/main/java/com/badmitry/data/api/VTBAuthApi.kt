package com.badmitry.data.api

import com.badmitry.domain.entities.AuthToken
import com.badmitry.domain.entities.TokenFailed
import io.reactivex.Single
import retrofit2.http.*

interface VTBAuthApi {
    @FormUrlEncoded
    @POST("/passport/oauth2/token")
    fun auth(
        @Query("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Header("Host") host: String
    ): Single<AuthToken>

    @GET("/api/test")
    fun checkToken(
        @Header("Authorization") token: String
    ): Single<TokenFailed>
}