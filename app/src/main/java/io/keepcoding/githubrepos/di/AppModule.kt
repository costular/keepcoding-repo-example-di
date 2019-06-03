package io.keepcoding.githubrepos.di

import android.app.Application
import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.keepcoding.githubrepos.MainRepository
import io.keepcoding.githubrepos.MainRepositoryImpl
import io.keepcoding.githubrepos.api.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun provideRetrofit(url: String): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)

    @Singleton
    @Provides
    fun provideRepository(githubService: GithubService): MainRepository =
        MainRepositoryImpl(githubService)

}