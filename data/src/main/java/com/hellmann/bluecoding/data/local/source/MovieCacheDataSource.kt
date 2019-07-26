package com.hellmann.bluecoding.data.local.source

import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

interface MovieCacheDataSource {
    fun getMovie(id: Int): Single<Movie>
    fun getMovies(): Single<List<Movie>>
    fun insertData(list: List<Movie>)
    fun updateData(list: List<Movie>)
}