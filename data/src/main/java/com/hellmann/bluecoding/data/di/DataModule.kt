package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.MovieRepositoryImpl
import com.hellmann.bluecoding.domain.repository.MovieRepository
import org.koin.dsl.module


val repositoryModule = module {
    factory<MovieRepository> {
        MovieRepositoryImpl(
            cacheDataSource = get(), remoteDataSource = get())
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)