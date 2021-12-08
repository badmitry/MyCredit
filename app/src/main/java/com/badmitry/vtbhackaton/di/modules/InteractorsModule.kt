package com.badmitry.vtbhackaton.di.modules

import com.badmitry.domain.interactors.VTBAuthInteractor
import com.badmitry.domain.interactors.VTBCreditInteractor
import com.badmitry.domain.interactors.YandexPartitionsInteractor
import com.badmitry.domain.repositories.INetworkChecker
import com.badmitry.domain.repositories.IVTBAuthRepositories
import com.badmitry.domain.repositories.IYandexPartitionsRepositories
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Singleton
    @Provides
    fun vtbAuthInteractor(
        @Named("vtbAuth") vtbAuthRepositories: IVTBAuthRepositories,
        networkChecker: INetworkChecker,
        @Named("IoScheduler") scheduler: Scheduler,
        @Named("MainScheduler") postScheduler: Scheduler
    ): VTBAuthInteractor =
        VTBAuthInteractor(vtbAuthRepositories, networkChecker, scheduler, postScheduler)

    @Singleton
    @Provides
    fun vtbCreditInteractor(
        @Named("vtbAuth") vtbAuthRepositories: IVTBAuthRepositories,
        networkChecker: INetworkChecker,
        @Named("IoScheduler") scheduler: Scheduler,
        @Named("MainScheduler") postScheduler: Scheduler
    ): VTBCreditInteractor =
        VTBCreditInteractor(vtbAuthRepositories, networkChecker, scheduler, postScheduler)

    @Singleton
    @Provides
    fun yandexPartitionsInteractor(
        @Named("yandex") yandexPartitionsRepositories: IYandexPartitionsRepositories,
        networkChecker: INetworkChecker,
        @Named("IoScheduler") scheduler: Scheduler,
        @Named("MainScheduler") postScheduler: Scheduler
    ): YandexPartitionsInteractor =
        YandexPartitionsInteractor(
            yandexPartitionsRepositories,
            networkChecker,
            scheduler,
            postScheduler
        )
}
