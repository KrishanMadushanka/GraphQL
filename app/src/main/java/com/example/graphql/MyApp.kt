package com.example.graphql

import android.app.Application
import com.example.graphql.DI.AppComponent
import com.example.graphql.DI.DaggerAppComponent
import com.example.graphql.DI.NetModule


class MyApp : Application() {
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder()
                .netModule(NetModule("https://api.github.com/"))
                .build()
    }
}