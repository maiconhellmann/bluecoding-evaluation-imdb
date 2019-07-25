package com.hellmann.bluecoding.domain.repository

import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getMovies(forceUpdate: Boolean): Single<List<Movie>>
}