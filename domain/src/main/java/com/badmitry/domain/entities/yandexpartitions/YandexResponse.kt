package com.badmitry.domain.entities.yandexpartitions

import com.badmitry.domain.entities.Banks
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class YandexResponse(
    @Expose @SerializedName("features") val features: List<Partitions>,
    var bank: Banks? = null
) : Serializable