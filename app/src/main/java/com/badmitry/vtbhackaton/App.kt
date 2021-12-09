package com.badmitry.vtbhackaton

import com.badmitry.data.db.AppDatabase
import com.badmitry.vtbhackaton.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory
import dagger.android.DaggerApplication


class App : DaggerApplication() {
    private val MAPKIT_API_KEY = "8ed48b1c-8237-4f35-a5ad-2bdab1c4bb2a"

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)
        AppDatabase.createInstance(this)
    }
}