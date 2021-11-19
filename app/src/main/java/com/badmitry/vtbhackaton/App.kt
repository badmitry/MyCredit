package com.badmitry.vtbhackaton

import com.badmitry.vtbhackaton.di.DaggerAppComponent
import dagger.android.DaggerApplication


class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()
}