package io.keepcoding.githubrepos

import android.app.Application
import io.keepcoding.githubrepos.di.appModule
import io.keepcoding.githubrepos.di.signUpModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    appModule,
                    signUpModule
                )
            )
        }
    }

}