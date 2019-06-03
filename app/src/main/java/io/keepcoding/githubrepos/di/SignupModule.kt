package io.keepcoding.githubrepos.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

val signUpModule = module {

    single(qualifier = named("production")) {
        "Hola mundo desde production"
    }

}