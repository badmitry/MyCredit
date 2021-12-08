package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressInfo(
    @Expose @SerializedName("block") val block: String = "heff",
    @Expose @SerializedName("city") val city: String = "Москва",
    @Expose @SerializedName("district") val district: String = "izoapihupujoc",
    @Expose @SerializedName("flat") val flat: String = "6297",
    @Expose @SerializedName("house") val house: String = "codcibazti",
    @Expose @SerializedName("locality") val locality: String = "Москва",
    @Expose @SerializedName("region") val region: String = "ALTAY",
    @Expose @SerializedName("street") val street: String = "проспект",
    @Expose @SerializedName("zip") val zip: String = "085899"
) : Serializable