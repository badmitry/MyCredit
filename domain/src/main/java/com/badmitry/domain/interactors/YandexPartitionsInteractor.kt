package com.badmitry.domain.interactors

import com.badmitry.domain.entities.Bbox
import com.badmitry.domain.entities.yandexpartitions.Partitions
import com.badmitry.domain.entities.yandexpartitions.YandexResponse
import com.badmitry.domain.exceptions.InternetConnectionException
import com.badmitry.domain.repositories.INetworkChecker
import com.badmitry.domain.repositories.IYandexPartitionsRepositories
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class YandexPartitionsInteractor @Inject constructor(
    private val yandexPartitionsRepositories: IYandexPartitionsRepositories,
    private val networkChecker: INetworkChecker,
    @Named("IoScheduler") scheduler: Scheduler,
    @Named("MainScheduler") postScheduler: Scheduler
) : BaseInteractor<YandexResponse, YandexPartitionsInteractor.Params>(scheduler, postScheduler) {
    data class Params(val bbox: Bbox)

    override fun createSingle(params: Params): Single<YandexResponse> {
        return networkChecker.isConnect().flatMap {
            if (it) {
                return@flatMap yandexPartitionsRepositories.getVtbPartitions(params.bbox)
            } else {
                return@flatMap Single.create<YandexResponse> { emitter ->
                    emitter.onError(InternetConnectionException())
                }
            }
        }
    }
}