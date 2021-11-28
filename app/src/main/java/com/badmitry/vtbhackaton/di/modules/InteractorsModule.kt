package com.badmitry.vtbhackaton.di.modules

import com.badmitry.domain.interactors.VTBAuthInteractor
import com.badmitry.domain.repositories.INetworkChecker
import com.badmitry.domain.repositories.IVTBAuthRepositories
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
class InteractorsModule {

    @Singleton
    @Provides
    fun networkChecker(
        vtbAuthRepositories: IVTBAuthRepositories,
        networkChecker: INetworkChecker,
        @Named("IoScheduler") scheduler: Scheduler,
        @Named("MainScheduler") postScheduler: Scheduler
    ): VTBAuthInteractor =
        VTBAuthInteractor(vtbAuthRepositories, networkChecker, scheduler, postScheduler)
}
