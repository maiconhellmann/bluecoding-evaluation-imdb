package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.local.database.MovieDataBase
import com.hellmann.bluecoding.data.local.source.MovieCacheDataSource
import com.hellmann.bluecoding.data.local.source.MovieCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    single { MovieDataBase.createDatabase(androidContext()) }
    factory<MovieCacheDataSource> { MovieCacheDataSourceImpl(movieDao = get()) }
}