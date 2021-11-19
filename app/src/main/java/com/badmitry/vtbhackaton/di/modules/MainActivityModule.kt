package com.badmitry.vtbhackaton.di.modules

import androidx.lifecycle.ViewModel
import com.badmitry.vtbhackaton.di.anotation.ViewModelKey
import com.badmitry.vtbhackaton.viewmodules.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun userDevicesViewModel(viewModel: MainViewModel): ViewModel
}
