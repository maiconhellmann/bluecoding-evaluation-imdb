package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.AuthenticationRepositoryImpl
import com.hellmann.bluecoding.data.MovieRepositoryImpl
import com.hellmann.bluecoding.domain.repository.AuthenticationRepository
import com.hellmann.bluecoding.domain.repository.MovieRepository
import org.koin.dsl.module


val repositoryModule = module {
    //Movie
    factory<MovieRepository> {
        MovieRepositoryImpl(cacheDataSource = get(), remoteDataSource = get())
    }
    //Authentication
    factory<AuthenticationRepository> {
        AuthenticationRepositoryImpl(cacheDataSource = get(), remoteDataSource = get())
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)