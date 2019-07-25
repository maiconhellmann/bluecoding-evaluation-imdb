package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.MovieRepositoryImpl
import com.hellmann.bluecoding.domain.repository.MovieRepository
import org.koin.dsl.module

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 25/05/2019
 * 
 * (c) 2019 
 */
val repositoryModule = module {
    factory<MovieRepository> {
        MovieRepositoryImpl(
            cacheDataSource = get(), remoteDataSource = get())
    }
}

val dataModules = listOf(remoteDataSourceModule, repositoryModule, cacheDataModule)