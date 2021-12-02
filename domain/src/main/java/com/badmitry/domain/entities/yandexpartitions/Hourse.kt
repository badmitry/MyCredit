package com.badmitry.domain.entities.yandexpartitions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Hourse(
    @Expose @SerializedName("text") val text: String
) : Serializable