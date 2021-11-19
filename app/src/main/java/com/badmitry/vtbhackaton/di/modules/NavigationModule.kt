package com.badmitry.vtbhackaton.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun getMainCicerone() = Cicerone.create()

    @Singleton
    @Provides
    fun getMainRouter(cicerone: Cicerone<Router>) = cicerone.router

    @Singleton
    @Provides
    fun getMainNavigatorHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder
}
