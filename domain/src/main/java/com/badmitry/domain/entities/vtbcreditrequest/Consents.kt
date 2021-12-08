package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Consents(
    @Expose @SerializedName("consentType") val consentType: String = "AGREEMENT_BKI",
    @Expose @SerializedName("endDate") val endDate: String = "2030-06-29",
    @Expose @SerializedName("isObtained") val isObtained: Boolean = true,
    @Expose @SerializedName("obtainedDate") val obtainedDate: String = "2020-06-29"
) : Serializable