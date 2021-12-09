package com.badmitry.domain.interactors

import com.badmitry.domain.entities.AuthData
import com.badmitry.domain.entities.vtbcreditrequest.VtbApplicationId
import com.badmitry.domain.entities.vtbcreditrequest.VtbCreditRequest
import com.badmitry.domain.exceptions.InternetConnectionException
import com.badmitry.domain.repositories.INetworkChecker
import com.badmitry.domain.repositories.ISaverRepositories
import com.badmitry.domain.repositories.IVTBAuthRepositories
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class VTBCreditInteractor @Inject constructor(
    private val vtbAuthRepositories: IVTBAuthRepositories,
    private val saveRepositories: ISaverRepositories,
    private val networkChecker: INetworkChecker,
    @Named("IoScheduler") scheduler: Scheduler,
    @Named("MainScheduler") postScheduler: Scheduler
) : BaseInteractor<VtbApplicationId, VTBCreditInteractor.Params>(scheduler, postScheduler) {
    data class Params(val authData: AuthData)

    override fun createSingle(params: Params): Single<VtbApplicationId> {
        return networkChecker.isConnect().flatMap {
            if (it) {
                return@flatMap vtbAuthRepositories.creditApplication(
                    params.authData.token.accessToken,
                    VtbCreditRequest()
                ).flatMap {
                    saveRepositories.saveApplicationId(it).subscribe()
                    Single.create<VtbApplicationId> { emitter ->
                        emitter.onSuccess(it)
                    }
                }

            } else {
                return@flatMap Single.create<VtbApplicationId> { emitter ->
                    emitter.onError(InternetConnectionException())
                }
            }
        }
    }
}