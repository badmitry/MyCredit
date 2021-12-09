package com.badmitry.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(item: ApplicationIdEntity): Completable

    @Query("SELECT * FROM application")
    fun select(): Single<List<ApplicationIdEntity>>

    @Query("DELETE FROM application")
    fun clear(): Completable
}
