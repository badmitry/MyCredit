package com.badmitry.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "application")
data class ApplicationIdEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    var id: Long = 0L,

    @field:ColumnInfo(name = "applicationId")
    val applicationId: String
) : Serializable
