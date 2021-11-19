package com.badmitry.vtbhackaton.di.modules

import androidx.lifecycle.ViewModelProvider
import com.badmitry.vtbhackaton.viewmodules.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
