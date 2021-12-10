package com.badmitry.domain.interactors

import com.badmitry.domain.entities.Banks
import com.badmitry.domain.entities.Bbox
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
) : BaseInteractor<List<YandexResponse>, YandexPartitionsInteractor.Params>(scheduler, postScheduler) {
    data class Params(val bbox: Bbox)

    override fun createSingle(params: Params): Single<List<YandexResponse>> {
        val list = mutableListOf<YandexResponse>()
        return networkChecker.isConnect().flatMap {
            if (it) {
                return@flatMap yandexPartitionsRepositories.getVtbPartitions(params.bbox)
                    .flatMap { response ->
                        response.bank = Banks.VTB
                        list.add(response)
                        yandexPartitionsRepositories.getSberPartitions(params.bbox).map { response ->
                            response.bank = Banks.SBER
                            list.add(response)
                            return@map list
                        }
                    }
            } else {
                return@flatMap Single.create<List<YandexResponse>> { emitter ->
                    emitter.onError(InternetConnectionException())
                }
            }
        }
    }
}