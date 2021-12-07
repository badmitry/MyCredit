package com.badmitry.domain.interactors

import com.badmitry.domain.entities.AuthCredentials
import com.badmitry.domain.entities.AuthToken
import com.badmitry.domain.entities.User
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
) : BaseInteractor<User, VTBAuthInteractor.Params>(scheduler, postScheduler) {
    data class Params(val authCredentials: AuthCredentials)

    override fun createSingle(params: Params): Single<User> {
        return networkChecker.isConnect().flatMap {
            if (it) {
                return@flatMap vtbAuthRepositories.getToken(params.authCredentials).flatMap { token ->
                    vtbAuthRepositories.getUser(token.accessToken)
                }
            } else {
                return@flatMap Single.create<User> { emitter ->
                    emitter.onError(InternetConnectionException())
                }
            }
        }
    }
}