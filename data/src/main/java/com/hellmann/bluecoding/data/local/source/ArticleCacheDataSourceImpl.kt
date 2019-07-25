package com.hellmann.bluecoding.data.local.source

import com.hellmann.bluecoding.data.local.database.MovieDao
import com.hellmann.bluecoding.data.local.mapper.MovieCacheMapper
import com.hellmann.bluecoding.domain.entity.Movie
import io.reactivex.Single

class MovieCacheDataSourceImpl(private val MovieDao: MovieDao) : MovieCacheDataSource {

    override fun getMovies(): Single<List<Movie>> {
        return MovieDao.getAll().map { MovieCacheMapper.map(it) }
    }

    override fun insertData(list: List<Movie>) {
        MovieDao.insertAll(MovieCacheMapper.mapToCache(list))
    }

    override fun updateData(list: List<Movie>) {
        MovieDao.updateDate(MovieCacheMapper.mapToCache(list))
    }
}