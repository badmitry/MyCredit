package com.badmitry.data.api

import com.badmitry.domain.entities.AuthToken
import com.badmitry.domain.entities.TokenFailed
import com.badmitry.domain.entities.User
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import com.badmitry.domain.entities.vtbcreditrequest.VtbCreditRequest
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

    @GET("/api/vtbid/v1/oauth2/me?=")
    fun getUser(
        @Header("Authorization") token: String
    ): Single<User>

    @POST("/api/rb/mssa/partner/hackathon/v1/applications")
    fun creditApplication(
        @Header("Authorization") token: String,
        @Body vtbCreditRequest: VtbCreditRequest
    ): Single<VtbApplicationId>
}