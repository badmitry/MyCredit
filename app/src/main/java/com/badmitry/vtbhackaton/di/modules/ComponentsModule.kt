package com.badmitry.vtbhackaton.di.modules

import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.di.modules.fragmentmodules.MainFragmentModule
import com.badmitry.vtbhackaton.di.modules.fragmentmodules.SelectPartitionFragmentModule
import com.badmitry.vtbhackaton.di.modules.fragmentmodules.SingingFragmentModule
import com.badmitry.vtbhackaton.fragments.FragmentMain
import com.badmitry.vtbhackaton.fragments.FragmentSelectPartition
import com.badmitry.vtbhackaton.fragments.FragmentSigning
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ComponentsModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SingingFragmentModule::class])
    fun fragmentSigning(): FragmentSigning

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun fragmentMain(): FragmentMain

    @ContributesAndroidInjector(modules = [SelectPartitionFragmentModule::class])
    fun fragmentSelectPartition(): FragmentSelectPartition
}
