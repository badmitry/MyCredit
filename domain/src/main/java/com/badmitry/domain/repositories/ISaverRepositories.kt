package com.badmitry.domain.repositories

import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import io.reactivex.Completable
import io.reactivex.Single

interface ISaverRepositories {
    fun saveApplicationId(applicationId: VtbApplicationId): Completable
    fun getApplicationId(): Single<List<VtbApplicationId>>
    fun deleteByApplicationId(applicationId: String): Completable
}