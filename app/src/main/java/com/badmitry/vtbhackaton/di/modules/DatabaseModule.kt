package com.badmitry.vtbhackaton.di.modules

import com.badmitry.data.repositories.DbRepositories
import com.badmitry.domain.repositories.ISaverRepositories
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseRepository(): ISaverRepositories = DbRepositories()
}
