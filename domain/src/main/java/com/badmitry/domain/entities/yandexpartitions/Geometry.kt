package com.badmitry.domain.entities.yandexpartitions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geometry(
    @Expose @SerializedName("type") val type: String,
    @Expose @SerializedName("coordinates") val coordinates: List<String>
) : Serializable