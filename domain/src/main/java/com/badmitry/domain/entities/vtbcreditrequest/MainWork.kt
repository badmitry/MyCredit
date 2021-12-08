package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainWork(
    @Expose @SerializedName("addressInfo") val addressInfo: AddressInfo = AddressInfo(),
    @Expose @SerializedName("companyInn") val companyInn: String = "772756377812",
    @Expose @SerializedName("companyName") val companyName: String = "Почта России",
    @Expose @SerializedName("employmentType") val employmentType: String = "DERIVATIVE",
    @Expose @SerializedName("jobPositionCategoryType") val jobPositionCategoryType: String = "TOP_MANAGER",
    @Expose @SerializedName("phoneNumber") val phoneNumber: String = "+73431978901",
    @Expose @SerializedName("workingExperienceCurrentType") val workingExperienceCurrentType: String = "FROM_3_TO_6_MONTHS",
    @Expose @SerializedName("workingExperienceType") val workingExperienceType: String = "FROM_1_TO_3_YEARS"
) : Serializable