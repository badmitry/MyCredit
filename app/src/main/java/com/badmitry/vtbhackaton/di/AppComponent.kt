package com.badmitry.vtbhackaton.di

import android.app.Application
import com.badmitry.vtbhackaton.App
import com.badmitry.vtbhackaton.di.modules.ComponentsModule
import com.badmitry.vtbhackaton.di.modules.NavigationModule
import com.badmitry.vtbhackaton.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        NavigationModule::class,
        ComponentsModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}