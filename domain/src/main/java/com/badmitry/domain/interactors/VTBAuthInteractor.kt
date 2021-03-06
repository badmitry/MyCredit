package com.badmitry.domain.interactors

import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthData
import com.badmitry.domain.exceptions.InternetConnectionException
import com.badmitry.domain.repositories.INetworkChecker
import com.badmitry.domain.repositories.IVTBAuthRepositories
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class VTBAuthInteractor @Inject constructor(
    private val vtbAuthRepositories: IVTBAuthRepositories,
    private val networkChecker: INetworkChecker,
    @Named("IoScheduler") scheduler: Scheduler,
    @Named("MainScheduler") postScheduler: Scheduler
) : BaseInteractor<AuthData, VTBAuthInteractor.Params>(scheduler, postScheduler) {
    data class Params(val authCredentials: AuthCredentials)

    override fun createSingle(params: Params): Single<AuthData> {
        return networkChecker.isConnect().flatMap {
            if (it) {
                return@flatMap vtbAuthRepositories.getToken(params.authCredentials)
                    .flatMap { token ->
                        vtbAuthRepositories.getUser(token.accessToken).map { AuthData(token, it) }
                    }
            } else {
                return@flatMap Single.create<AuthData> { emitter ->
                    emitter.onError(InternetConnectionException())
                }
            }
        }
    }
}