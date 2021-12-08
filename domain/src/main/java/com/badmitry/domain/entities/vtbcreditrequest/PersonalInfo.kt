package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PersonalInfo(
    @Expose @SerializedName("birthDate") val birthDate: String = "2020-06-29",
    @Expose @SerializedName("birthPlace") val birthPlace: String = "7/16/1989",
    @Expose @SerializedName("fio") val fio: Fio = Fio(),
    @Expose @SerializedName("genderType") val genderType: String = "MALE",
    @Expose @SerializedName("previousFio") val previousFio: Fio = Fio(),
    @Expose @SerializedName("wasLastNameChanged") val wasLastNameChanged: Boolean = false
) : Serializable