package com.hellmann.bluecoding.data.di

import com.hellmann.bluecoding.data.local.database.ArticleDataBase
import com.hellmann.bluecoding.data.local.source.ArticleCacheDataSource
import com.hellmann.bluecoding.data.local.source.ArticleCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 25/05/2019
 * 
 * (c) 2019 
 */
val cacheDataModule = module {
    single { ArticleDataBase.createDatabase(androidContext()) }
    factory<ArticleCacheDataSource> { ArticleCacheDataSourceImpl(articleDao = get()) }
}