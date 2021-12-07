package com.badmitry.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @Expose @SerializedName("surname") val surname: String,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("patronymic") val patronymic: String
) : Serializable