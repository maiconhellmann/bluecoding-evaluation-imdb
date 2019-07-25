package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.local.database.MovieDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 09/06/2019
 * 
 * (c) 2019 
 */

val cacheDataModuleTest = module {
    single { MovieDataBase.createDatabaseInMemory(androidContext()) }
}
