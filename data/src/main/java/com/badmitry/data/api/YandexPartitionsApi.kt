package com.badmitry.data.api

import com.badmitry.domain.entities.yandexpartitions.YandexResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexPartitionsApi {

    @GET("/v1/?text=отделения ВТБ&results=500&rspn=1&type=biz&lang=ru_RU&apikey=df130958-bdfb-486d-be97-4a0e9eb8ee97")
    fun getVtbPartitions(
        @Query("bbox") bbox: String
    ): Single<YandexResponse>
}