package com.hellmann.bluecoding.domain.di

import com.hellmann.bluecoding.domain.usecase.GetArticlesUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        GetArticlesUseCase(
            repository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCaseModule)