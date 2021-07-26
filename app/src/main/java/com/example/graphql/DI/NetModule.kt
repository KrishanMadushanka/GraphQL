package com.example.graphql.DI

import android.app.Application
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
class NetModule
    (var mBaseUrl: String) {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(mBaseUrl)
            .build()
    }
}