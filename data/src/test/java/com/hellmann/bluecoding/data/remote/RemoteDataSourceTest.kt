package com.hellmann.bluecoding.data.remote

import com.hellmann.bluecoding.data.remote.api.ServerApi
import com.hellmann.bluecoding.data.remote.source.RemoteDataSource
import com.hellmann.bluecoding.data.remote.source.RemoteDataSourceImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RemoteDataSourceTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var serverApi: ServerApi

    @Before
    fun prepare() {
        serverApi = mock(ServerApi::class.java)
        remoteDataSource = RemoteDataSourceImpl(serverApi)
    }

    @Test
    fun `movie list is empty`() {
        //TODO testing mocking
//        `when`(serverApi.fetchMoviesByYear()).then {
//            Single.just(
//                MovieListPayload(
//                    "ok", 0, emptyList())
//            )
//        }
//        with(remoteDataSource.getMovies().test()) {
//            assertValue {
//                it.isEmpty()
//            }
//            assertValueCount(1)
//        }
    }

    @Test
    fun `movie list not empty`() {
        //TODO testing and mocking
//        `when`(serverApi.fetchMoviesByYear()).then {
//            Single.just(
//                MoviesPayload(
//                    "ok", 1, listOf(MoviePayload("", ""))))
//        }
//
//        with(remoteDataSource.getMovies().test()) {
//            assertValue { it.isNotEmpty() }
//            assertValueCount(1)
//        }
    }
}