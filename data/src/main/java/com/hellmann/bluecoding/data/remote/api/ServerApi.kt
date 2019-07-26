package com.hellmann.bluecoding.data.remote.api

import com.hellmann.bluecoding.data.BuildConfig
import com.hellmann.bluecoding.data.remote.model.MovieListPayload
import com.hellmann.bluecoding.data.remote.model.MoviePayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {

    @GET("${BuildConfig.API_VERSION}/movie/popular?api_key=${BuildConfig.API_KEY}")
    fun fetchMoviesByYear(@Query("year") year: String): Single<MovieListPayload>

    @GET("${BuildConfig.API_VERSION}/search/movie?api_key=${BuildConfig.API_KEY}")
    fun searchMovies(@Query("query") query: String): Single<MovieListPayload>

    @GET("${BuildConfig.API_VERSION}/movie?api_key=${BuildConfig.API_KEY}")
    fun fetchMovieDetail(@Query("movie_id") movieId: Int): Single<MoviePayload>
}