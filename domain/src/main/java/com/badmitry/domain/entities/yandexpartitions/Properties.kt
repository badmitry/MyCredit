package com.badmitry.domain.entities.yandexpartitions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Properties(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("description") val description: String,
    @Expose @SerializedName("CompanyMetaData") val companyMetaData: CompanyMetaData
) : Serializable