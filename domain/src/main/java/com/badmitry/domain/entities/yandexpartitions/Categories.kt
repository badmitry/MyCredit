package com.badmitry.domain.entities.yandexpartitions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Categories(
    @Expose @SerializedName("class") val clas: String,
    @Expose @SerializedName("name") val name: String
) : Serializable