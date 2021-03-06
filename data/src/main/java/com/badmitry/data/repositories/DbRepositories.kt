package com.badmitry.data.repositories

import com.badmitry.data.db.AppDatabase
import com.badmitry.data.mappers.ApplicationMapper
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import com.badmitry.domain.repositories.ISaverRepositories
import io.reactivex.Completable
import io.reactivex.Single

class DbRepositories : ISaverRepositories {
    override fun saveApplicationId(applicationId: VtbApplicationId): Completable {
        return AppDatabase.getInstance().dao()
            .insertReplace(ApplicationMapper.getApplicationDbFromResponse(applicationId))
    }

    override fun getApplicationId(): Single<List<VtbApplicationId>> {
        return AppDatabase.getInstance().dao()
            .select().map {
                ApplicationMapper.getApplicationIdFromDb(it)
            }
    }

    override fun deleteByApplicationId(applicationId: String): Completable {
        return AppDatabase.getInstance().dao()
            .deleteByApplicationId(applicationId)
    }

}