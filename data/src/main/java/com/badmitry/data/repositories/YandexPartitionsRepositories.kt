package com.badmitry.data.repositories

import com.badmitry.data.api.YandexPartitionsApi
import com.badmitry.domain.entities.Bbox
import com.badmitry.domain.entities.yandexpartitions.Partitions
import com.badmitry.domain.entities.yandexpartitions.YandexResponse
import com.badmitry.domain.repositories.IYandexPartitionsRepositories
import io.reactivex.Single

class YandexPartitionsRepositories(private val api: YandexPartitionsApi) :
    IYandexPartitionsRepositories {
    override fun getVtbPartitions(bbox: Bbox): Single<YandexResponse> {
        return api.getVtbPartitions(bbox.getBbox())
    }

    override fun getSberPartitions(bbox: Bbox): Single<YandexResponse> {
        return api.getSberPartitions(bbox.getBbox())
    }
}