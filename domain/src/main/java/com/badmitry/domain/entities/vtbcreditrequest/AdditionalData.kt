package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AdditionalData(
    @Expose @SerializedName("creditTarget") val creditTarget: String = "6377023753272081",
    @Expose @SerializedName("preferredOffice") val preferredOffice: PrefferedOffice = PrefferedOffice()
) : Serializable