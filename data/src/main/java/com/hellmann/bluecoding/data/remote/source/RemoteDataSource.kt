package com.hellmann.bluecoding.data.remote.source

import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

interface RemoteDataSource {
    fun getMovies(): Single<List<Movie>>
}