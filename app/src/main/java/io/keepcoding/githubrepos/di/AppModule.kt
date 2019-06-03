package io.keepcoding.githubrepos.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.keepcoding.githubrepos.MainRepository
import io.keepcoding.githubrepos.MainRepositoryImpl
import io.keepcoding.githubrepos.MainViewModel
import io.keepcoding.githubrepos.api.GithubService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    single {
        get<Retrofit>().create(GithubService::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(qualifier = named("production")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
    }

    viewModel {
        MainViewModel(get())
    }

}