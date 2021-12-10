package com.badmitry.domain.repositories

import com.badmitry.domain.entities.Bbox
import com.badmitry.domain.entities.yandexpartitions.YandexResponse
import io.reactivex.Single

interface IYandexPartitionsRepositories {
    fun getVtbPartitions(bbox: Bbox): Single<YandexResponse>
    fun getSberPartitions(bbox: Bbox): Single<YandexResponse>
}