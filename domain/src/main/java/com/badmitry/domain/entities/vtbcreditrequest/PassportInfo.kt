package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PassportInfo(
    @Expose @SerializedName("issueCode") val issueCode: String = "456-089",
    @Expose @SerializedName("issueDate") val issueDate: String = "2020-06-29",
    @Expose @SerializedName("issueName") val issueName: String = "Отделом милиции-1 УВД гор. Ангарска Иркутской обл.",
    @Expose @SerializedName("number") val number: String = "992345",
    @Expose @SerializedName("series") val series: String = "6405"
) : Serializable