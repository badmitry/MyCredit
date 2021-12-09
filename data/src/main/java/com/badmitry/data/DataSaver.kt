package com.badmitry.data

import com.badmitry.domain.entities.AuthData
import com.badmitry.domain.entities.yandexpartitions.Partitions

class DataSaver {
    var authData: AuthData? = null
    var partitions: Partitions? = null

    companion object {
        val instance = DataSaver()
    }
}