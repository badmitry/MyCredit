package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FamilyInfo(
    @Expose @SerializedName("maritalStatusType") val maritalStatusType: String = "SINGLE",
    @Expose @SerializedName("underageChildrenNumber") val underageChildrenNumber: Int = 2
) : Serializable