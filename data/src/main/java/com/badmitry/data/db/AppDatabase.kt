package com.badmitry.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false,
    entities = [ApplicationIdEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        private const val DB_NAME = "app_database.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun createInstance(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): AppDatabase {
            if (instance == null) throw NullPointerException("AppDatabase instance is null")
            return instance!!
        }
    }
}
