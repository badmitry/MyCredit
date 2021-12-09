package com.badmitry.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserPassport(
    @Expose @SerializedName("series") val series: String,
    @Expose @SerializedName("number") val number: String,
    @Expose @SerializedName("issueDate") val issueDate: String,
    @Expose @SerializedName("departmentDoc") val departmentDoc: String,
    @Expose @SerializedName("lastName") val lastName: String,
    @Expose @SerializedName("firstName") val firstName: String,
    @Expose @SerializedName("gender") val gender: String,
    @Expose @SerializedName("birthDate") val birthDate: String,
    @Expose @SerializedName("birthPlace") val birthPlace: String,
    @Expose @SerializedName("issuedBy") val issuedBy: String,
    @Expose @SerializedName("issueId") val issueId: String
) : Serializable