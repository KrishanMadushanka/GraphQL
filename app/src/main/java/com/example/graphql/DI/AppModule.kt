package com.example.graphql.DI

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(application: Application) {
    var mApplication: Application
    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }

    init {
        mApplication = application
    }
}