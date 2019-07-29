package com.hellmann.bluecoding.domain.di

import com.hellmann.bluecoding.domain.usecase.AuthenticationUseCase
import com.hellmann.bluecoding.domain.usecase.GetMoviesUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        GetMoviesUseCase(
            repository = get(), scheduler = Schedulers.io())
    }
    factory {
        AuthenticationUseCase(
            repository = get(), scheduler = Schedulers.io())
    }
}

val domainModule = listOf(useCaseModule)