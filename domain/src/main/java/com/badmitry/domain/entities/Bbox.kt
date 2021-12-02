package com.badmitry.domain.entities

data class Bbox(
    val bottomLeftLatitude: String,
    val bottomLeftLongitude: String,
    val topRightLatitude: String,
    val topRightLongitude: String
) {
    fun getBbox(): String {
        return "$bottomLeftLatitude,$bottomLeftLongitude~$topRightLatitude,$topRightLongitude"
    }
}
