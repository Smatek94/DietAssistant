package com.skolimowskim.dietassistant.app

import android.app.Application
import com.skolimowskim.dietassistant.dagger.AppComponent
import com.skolimowskim.dietassistant.dagger.DaggerAppComponent
import com.skolimowskim.dietassistant.dagger.ViewModelModule

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
                .builder()
//                .configModule(ConfigModule())
//                .mainModule(MainModule(this))
//                .netModule(NetModule())
//                .viewModelModule(ViewModelModule())
                .build()
    }
}