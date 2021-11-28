package com.badmitry.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthToken(
    @Expose @SerializedName("access_token") val accessToken: String,
    @Expose @SerializedName("expires_in") val expiresIn: Int,
    @Expose @SerializedName("token_type") val tokenType: String
) : Serializable