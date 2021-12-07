package com.badmitry.vtbhackaton.di.modules.fragmentmodules

import androidx.lifecycle.ViewModel
import com.badmitry.vtbhackaton.di.anotation.ViewModelKey
import com.badmitry.vtbhackaton.viewmodules.FragmentMainViewModel
import com.badmitry.vtbhackaton.viewmodules.FragmentSigningViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(FragmentMainViewModel::class)
    fun mainViewModel(fragmentViewModel: FragmentMainViewModel): ViewModel
}
