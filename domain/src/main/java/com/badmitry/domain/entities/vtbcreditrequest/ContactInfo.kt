package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ContactInfo(
    @Expose @SerializedName("emailAddress") val emailAddress: String = "example@example.ru",
    @Expose @SerializedName("phoneNumber") val phoneNumber: String = "+72009897095"
) : Serializable