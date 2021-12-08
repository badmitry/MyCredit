package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainIncomeInfo(
    @Expose @SerializedName("amount") val amount: Int = 200000,
    @Expose @SerializedName("incomeType") val incomeType: String = "BASIC"
) : Serializable