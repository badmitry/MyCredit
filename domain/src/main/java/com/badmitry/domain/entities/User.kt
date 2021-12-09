package com.badmitry.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @Expose @SerializedName("surname") val surname: String,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("patronymic") val patronymic: String,
    @Expose @SerializedName("gender") val gender: String,
    @Expose @SerializedName("birthPlace") val birthPlace: String,
    @Expose @SerializedName("birthDate") val birthDate: String,
    @Expose @SerializedName("maritalStatus") val maritalStatus: String,
    @Expose @SerializedName("mobilePhone") val mobilePhone: String,
    @Expose @SerializedName("email") val email: String,
    @Expose @SerializedName("registrationAddress") val registrationAddress: UserAdress,
    @Expose @SerializedName("temporaryAddress") val temporaryAddress: UserAdress,
    @Expose @SerializedName("actualAddress") val actualAddress: UserAdress,
    @Expose @SerializedName("snils") val snils: String,
    @Expose @SerializedName("inn") val inn: String,
    @Expose @SerializedName("rfPassport") val rfPassport: UserPassport,
    @Expose @SerializedName("mdmId") val mdmId: String,
) : Serializable