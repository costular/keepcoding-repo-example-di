package io.keepcoding.githubrepos.di

import dagger.Component
import io.keepcoding.githubrepos.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}