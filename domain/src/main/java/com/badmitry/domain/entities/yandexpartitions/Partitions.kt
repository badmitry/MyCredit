package com.badmitry.domain.entities.yandexpartitions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Partitions(
    @Expose @SerializedName("type") val type: String,
    @Expose @SerializedName("geometry") val geometry: Geometry,
    @Expose @SerializedName("properties") val properties: Properties
) : Serializable