package io.keepcoding.githubrepos

import android.app.Application
import io.keepcoding.githubrepos.di.AppComponent
import io.keepcoding.githubrepos.di.AppModule
import io.keepcoding.githubrepos.di.DaggerAppComponent

class MainApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}