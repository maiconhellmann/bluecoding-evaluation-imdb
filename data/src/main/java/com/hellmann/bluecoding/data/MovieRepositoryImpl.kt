package com.hellmann.bluecoding.data

import com.hellmann.bluecoding.data.local.source.MovieCacheDataSource
import com.hellmann.bluecoding.data.remote.source.RemoteDataSource
import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.domain.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImpl(
    private val cacheDataSource: MovieCacheDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override fun getMovies(forceUpdate: Boolean): Single<List<Movie>> {
        return if (forceUpdate) {
            getMoviesRemote(forceUpdate)
        } else {
            cacheDataSource.getMovies().flatMap { movieList ->
                when {
                    movieList.isEmpty() -> getMoviesRemote(false)
                    else -> Single.just(movieList)
                }
            }
        }
    }

    private fun getMoviesRemote(forceUpdate: Boolean): Single<List<Movie>> {
        return remoteDataSource.getMovies().flatMap { articleList ->
            if (forceUpdate) {
                cacheDataSource.updateData(articleList)
            } else {
                cacheDataSource.insertData(articleList)
            }
            Single.just(articleList)
        }
    }
}