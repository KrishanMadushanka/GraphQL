package com.example.graphql.DI

import com.example.graphql.MainActivity
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface AppComponent {
    fun inject(activity: MainActivity?)
}