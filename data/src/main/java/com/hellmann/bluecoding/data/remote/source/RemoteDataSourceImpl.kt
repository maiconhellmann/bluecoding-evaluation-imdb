package com.hellmann.bluecoding.data.remote.source

import com.hellmann.bluecoding.data.remote.api.ServerApi
import com.hellmann.bluecoding.data.remote.mapper.MoviePayloadMapper
import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

class RemoteDataSourceImpl(private val movieApi: ServerApi) : RemoteDataSource {
    override fun getMovies(year: String): Single<List<Movie>> {
        return movieApi.fetchMoviesByYear(year).map { MoviePayloadMapper.map(it) }
    }

    override fun searchMovies(query: String): Single<List<Movie>> {
        return movieApi.searchMovies(query).map { MoviePayloadMapper.map(it) }
    }

    override fun getMovieDetails(id: Int): Single<Movie> {
        return movieApi.fetchMovieDetail(id).map { MoviePayloadMapper.map(it) }
    }

    override fun getMoviesInTheater(): Single<List<Movie>> {
        return movieApi.fetchMoviesInTheater().map { MoviePayloadMapper.map(it) }
    }
}