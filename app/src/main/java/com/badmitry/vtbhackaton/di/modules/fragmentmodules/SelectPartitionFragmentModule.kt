package com.badmitry.vtbhackaton.di.modules.fragmentmodules

import androidx.lifecycle.ViewModel
import com.badmitry.vtbhackaton.di.anotation.ViewModelKey
import com.badmitry.vtbhackaton.viewmodules.FragmentSelectPartitionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SelectPartitionFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(FragmentSelectPartitionViewModel::class)
    fun selectPartitionViewModel(fragmentViewModel: FragmentSelectPartitionViewModel): ViewModel
}
