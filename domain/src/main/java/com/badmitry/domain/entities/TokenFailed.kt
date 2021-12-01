package com.badmitry.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TokenFailed(
    @Expose @SerializedName("status") val status: String?
) : Serializable