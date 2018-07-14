package com.skolimowskim.dietassistant.dagger

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skolimowskim.dietassistant.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(val app: App) {

    @Provides
    @Singleton
    internal fun provideGson(): Gson =
            GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .setPrettyPrinting()
                    .create()

    @Provides
    @Singleton
    internal fun provideAppContext(): Context = app.applicationContext
}