package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.local.database.authentication.AuthenticationDataBase
import com.hellmann.bluecoding.data.local.database.movie.MovieDataBase
import com.hellmann.bluecoding.data.local.source.authentication.AuthenticationCacheDataSource
import com.hellmann.bluecoding.data.local.source.authentication.AuthenticationCacheDataSourceImpl
import com.hellmann.bluecoding.data.local.source.movie.MovieCacheDataSource
import com.hellmann.bluecoding.data.local.source.movie.MovieCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheDataModule = module {
    //Movie cache database
    single { MovieDataBase.createDatabase(androidContext()) }
    //Movie cache data source
    factory<MovieCacheDataSource> {
        MovieCacheDataSourceImpl(movieDao = get())
    }
    //Authentication cache database
    single { AuthenticationDataBase.createDatabase(androidContext()) }
    //Authentication cache data source
    factory<AuthenticationCacheDataSource> {
        AuthenticationCacheDataSourceImpl(dao = get())
    }
}