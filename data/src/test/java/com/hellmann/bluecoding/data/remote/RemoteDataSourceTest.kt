package com.hellmann.bluecoding.data.remote

import com.hellmann.bluecoding.data.MoviePayloadMock
import com.hellmann.bluecoding.data.remote.api.ServerApi
import com.hellmann.bluecoding.data.remote.model.MovieListPayload
import com.hellmann.bluecoding.data.remote.source.RemoteDataSource
import com.hellmann.bluecoding.data.remote.source.RemoteDataSourceImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.koin.core.KoinComponent
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will getMovies on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class RemoteDataSourceTest: KoinComponent {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var serverApi: ServerApi
    private val defaultMoviePayload= MoviePayloadMock.moviePlayloadMock.value
    private val defaultParameterYear = "2019"

    @Before
    fun prepare() {
        serverApi = mock(ServerApi::class.java)
        remoteDataSource = RemoteDataSourceImpl(serverApi)
    }

    @Test
    fun `movie list is empty`() {
        `when`(serverApi.fetchMoviesByYear(defaultParameterYear)).then {
            Single.just(
                MovieListPayload(
                    0, 0, 0, emptyList()))
        }
        with(remoteDataSource.getMovies(defaultParameterYear).test()) {
            assertValue {
                it.isEmpty()
            }
            assertValueCount(1)
        }
    }

    @Test
    fun `movie list not empty`() {
        `when`(serverApi.fetchMoviesByYear(defaultParameterYear)).then {
            Single.just(
                MovieListPayload(1, 1, 1, listOf(defaultMoviePayload)))
        }

        with(remoteDataSource.getMovies(defaultParameterYear).test()) {
            assertValue { it.isNotEmpty() }
            assertValueCount(1)
        }
    }
}