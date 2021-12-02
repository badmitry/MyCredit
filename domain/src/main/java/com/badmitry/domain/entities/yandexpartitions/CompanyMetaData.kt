package com.badmitry.domain.entities.yandexpartitions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CompanyMetaData(
    @Expose @SerializedName("address") val address: String,
    @Expose @SerializedName("Categories") val categories: List<Categories>,
    @Expose @SerializedName("Hours") val hourse: Hourse
) : Serializable