package com.badmitry.domain.entities.vtbcreditrequest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VtbCreditRequest(
    @Expose @SerializedName("additionalData") val additionalData: AdditionalData = AdditionalData(),
    @Expose @SerializedName("additionalIncomeInfo") val additionalIncomeInfo: AdditionalIncomeInfo = AdditionalIncomeInfo(),
    @Expose @SerializedName("cashLoanProduct") val cashLoanProduct: CashLoanProduct = CashLoanProduct(),
    @Expose @SerializedName("consents") val consents: List<Consents> = listOf(
        Consents("AGREEMENT_BKI"),
        Consents("AGREEMENT_MOBILE"),
        Consents("AGREEMENT_PERS")
    ),
    @Expose @SerializedName("contactInfo") val contactInfo: ContactInfo = ContactInfo(),
    @Expose @SerializedName("educationType") val educationType: String = "HIGHER_EDUCATION",
    @Expose @SerializedName("familyInfo") val familyInfo: FamilyInfo = FamilyInfo(),
    @Expose @SerializedName("mainIncomeInfo") val mainIncomeInfo: MainIncomeInfo = MainIncomeInfo(),
    @Expose @SerializedName("mainWork") val mainWork: MainWork = MainWork(),
    @Expose @SerializedName("partnerApplicationId") val partnerApplicationId: String = "1736347704360960",
    @Expose @SerializedName("partnerName") val partnerName: String = "Cora Curtis",
    @Expose @SerializedName("passportInfo") val passportInfo: PassportInfo = PassportInfo(),
    @Expose @SerializedName("personalInfo") val personalInfo: PersonalInfo = PersonalInfo(),
    @Expose @SerializedName("registrationAddressInfo") val registrationAddressInfo: AddressInfo = AddressInfo(),
    @Expose @SerializedName("residentialAddressInfo") val residentialAddressInfo: AddressInfo = AddressInfo()
) : Serializable