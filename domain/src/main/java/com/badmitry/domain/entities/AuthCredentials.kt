package com.badmitry.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthCredentials(
    @Expose @SerializedName("grant_type") val clientCredentials: String,
    @Expose @SerializedName("client_id") val clientId: String,
    @Expose @SerializedName("client_secret") val clientSecret: String,
    @Expose @SerializedName("host") val host: String
) : Serializable