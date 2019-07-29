package com.hellmann.bluecoding.data.remote.source

import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getMovies(year: String): Single<List<Movie>>
    fun searchMovies(query: String): Single<List<Movie>>
    fun getMovieDetails(id: Int): Single<Movie>
    fun getMoviesInTheater(): Single<List<Movie>>
}