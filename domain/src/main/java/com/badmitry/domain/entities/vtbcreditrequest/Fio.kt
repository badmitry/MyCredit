package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Fio(
    @Expose @SerializedName("firstName") val firstName: String = "Имя",
    @Expose @SerializedName("lastName") val lastName: String = "Фамилия",
    @Expose @SerializedName("patronymic") val patronymic: String = "Отчество"
) : Serializable