package com.hellmann.bluecoding.domain.repository

import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getMovies(forceUpdate: Boolean, currentYear: String): Single<List<Movie>>
    fun searchMovies(query: String): Single<List<Movie>>
}