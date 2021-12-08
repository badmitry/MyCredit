package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CashLoanProduct(
    @Expose @SerializedName("creditAmount") val creditAmount: Int = 50000,
    @Expose @SerializedName("creditPeriod") val creditPeriod: Int = 14,
    @Expose @SerializedName("productType") val productType: String = "KN"
) : Serializable