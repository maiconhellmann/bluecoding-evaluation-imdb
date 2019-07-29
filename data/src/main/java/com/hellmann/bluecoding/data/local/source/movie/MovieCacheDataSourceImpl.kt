package com.hellmann.bluecoding.data.local.source.movie

import com.hellmann.bluecoding.data.local.database.movie.MovieDao
import com.hellmann.bluecoding.data.local.mapper.MovieCacheMapper
import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

class MovieCacheDataSourceImpl(private val movieDao: MovieDao) :
    MovieCacheDataSource {

    override fun getMovies(): Single<List<Movie>> {
        return movieDao.getAll().map { MovieCacheMapper.map(it) }
    }

    override fun insertData(list: List<Movie>) {
        movieDao.insertAll(MovieCacheMapper.mapToCache(list))
    }

    override fun updateData(list: List<Movie>) {
        movieDao.updateDate(MovieCacheMapper.mapToCache(list))
    }

    override fun getMovie(id: Int): Single<Movie> {
        return movieDao.getById(id).map { MovieCacheMapper.map(it) }
    }
}