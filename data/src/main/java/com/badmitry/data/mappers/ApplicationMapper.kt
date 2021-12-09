package com.badmitry.data.mappers

import com.badmitry.data.db.ApplicationIdEntity
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId

object ApplicationMapper {
    fun getApplicationDbFromResponse(applicationId: VtbApplicationId): ApplicationIdEntity {
        return ApplicationIdEntity(0L, applicationId.applicationId)
    }

    fun getApplicationIdFromDb(applicationId: List<ApplicationIdEntity>): List<VtbApplicationId> {
        val application = mutableListOf<VtbApplicationId>()
        applicationId.forEach {
            application.add(VtbApplicationId(it.applicationId))
        }
        return application
    }
}