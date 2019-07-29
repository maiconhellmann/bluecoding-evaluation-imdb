package com.hellmann.bluecoding.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hellmann.bluecoding.data.remote.api.ServerApi
import com.hellmann.bluecoding.data.remote.model.MovieListPayload
import com.hellmann.bluecoding.data.remote.model.MoviePayload
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
    private lateinit var gson: Gson
    private lateinit var defaultMoviePayload: MoviePayload
    private val defaultParameterYear = "2019"

    @Before
    fun prepare() {
        serverApi = mock(ServerApi::class.java)
        remoteDataSource = RemoteDataSourceImpl(serverApi)
        gson = GsonBuilder().create()
        defaultMoviePayload = gson.fromJson(DEFAULT_MOVIE_PAYLOAD, MoviePayload::class.java)
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

    companion object {
        const val DEFAULT_MOVIE_PAYLOAD = """
{
    "vote_count": 7,
    "id": 613473,
    "video": false,
    "vote_average": 5.5,
    "title": "Burglary",
    "popularity": 614.73,
    "poster_path": "/xztkk3qwvjTfKArjTkhWUCuadFY.jpg",
    "original_language": "en",
    "original_title": "Burglary",
    "genre_ids": [
        27,
        53,
        9648
    ],
    "backdrop_path": null,
    "adult": false,
    "overview": "While playing videogames Mike spots a mysterious masked man outside his building.",
    "release_date": "2019-07-19"
}          
        """
    }
}