package com.hellmann.bluecoding.data.local

import androidx.test.platform.app.InstrumentationRegistry
import com.hellmann.bluecoding.data.di.cacheDataModuleTest
import com.hellmann.bluecoding.data.local.database.movie.MovieDao
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.get
import org.koin.test.AutoCloseKoinTest

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 09/06/2019
 * 
 * (c) 2019 
 */
class MovieDaoTest : AutoCloseKoinTest() {

    val movieDao = get<MovieDao>()

    @Before
    fun before() {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().context)
            modules(cacheDataModuleTest)
        }
    }

    @Test
    fun movieDaoTesting() {
        movieDao.apply {

            insertAll(listOf(
//                MovieCache()
            //TODO testing mock
            ))

            with(getAll().test()) {
                assertValue {
                    it.isNotEmpty()
                }
                //TODO testing assertions
//                assertValue { it.first().title == "title" }
//                assertValue { it.first().description == "desc" }
//                assertValue { it.first().url == "url" }
//                assertValue { it.first().urlToImage == "urlToImage" }
//                assertValue { it.first().id != 0L } //will be auto generated
            }

        }
    }
}