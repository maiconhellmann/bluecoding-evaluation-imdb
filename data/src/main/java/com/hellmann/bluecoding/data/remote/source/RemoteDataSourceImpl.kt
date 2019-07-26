package com.hellmann.bluecoding.data.remote.source

import com.hellmann.bluecoding.data.remote.api.ServerApi
import com.hellmann.bluecoding.data.remote.mapper.MoviePayloadMapper
import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

class RemoteDataSourceImpl(private val movieApi: ServerApi) : RemoteDataSource {
    override fun getMovies(year: String): Single<List<Movie>> {
        return movieApi.fetchMoviesByYear(year).map { MoviePayloadMapper.map(it) }
    }
}