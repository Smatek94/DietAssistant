package com.skolimowskim.dietassistant.app

import android.app.Application
import com.skolimowskim.dietassistant.dagger.*

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
                .builder()
//                .configModule(ConfigModule())
                .mainModule(MainModule(this))
                .repoModule(RepoModule())
                .viewModelModule(ViewModelModule())
                .build()
    }
}