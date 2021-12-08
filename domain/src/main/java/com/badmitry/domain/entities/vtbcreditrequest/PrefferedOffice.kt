package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PrefferedOffice(
    @Expose @SerializedName("address") val address: String = "468 Suwi Heights",
    @Expose @SerializedName("addressKladr") val addressKladr: String = "222 Dojuk Boulevard",
    @Expose @SerializedName("coordinates") val coordinates: String = "8.2209, -178.27916",
    @Expose @SerializedName("metro") val metro: String = "umuoftosraub",
    @Expose @SerializedName("name") val name: String = "Ricky Campbell",
    @Expose @SerializedName("number") val number: String = "8741247176933376",
    @Expose @SerializedName("officeId") val officeId: String = "2447184752541696",
    @Expose @SerializedName("opensOnHolidays") val opensOnHolidays: Boolean = true,
    @Expose @SerializedName("region") val region: String = "ALTAY",
    @Expose @SerializedName("regionOfObtaining") val regionOfObtaining: String = "vivtobzom",
    @Expose @SerializedName("workingHours") val workingHours: String = "22",
    @Expose @SerializedName("worksOnWeekends") val worksOnWeekends: Boolean = false
) : Serializable