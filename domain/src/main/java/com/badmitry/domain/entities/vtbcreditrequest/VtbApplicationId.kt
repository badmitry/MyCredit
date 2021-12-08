package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VtbApplicationId(
    @Expose @SerializedName("applicationId") val applicationId: String
) : Serializable