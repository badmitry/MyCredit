package com.badmitry.data

import com.badmitry.domain.entities.AuthData

class DataSaver {
    var authData: AuthData? = null

    companion object {
        val instance = DataSaver()
    }
}