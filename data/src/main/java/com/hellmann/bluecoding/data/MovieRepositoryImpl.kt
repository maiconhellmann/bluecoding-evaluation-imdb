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
    override fun getMovies(forceUpdate: Boolean, currentYear: String): Single<List<Movie>> {
        return if (forceUpdate) {
            getMoviesRemote(forceUpdate, currentYear)
        } else {
            cacheDataSource.getMovies().flatMap { movieList ->
                when {
                    movieList.isEmpty() -> getMoviesRemote(false, currentYear)
                    else -> Single.just(movieList)
                }
            }
        }
    }

    private fun getMoviesRemote(forceUpdate: Boolean, currentYear: String): Single<List<Movie>> {
        return remoteDataSource.getMovies(currentYear).flatMap { list ->
            if (forceUpdate) {
                cacheDataSource.updateData(list)
            } else {
                cacheDataSource.insertData(list)
            }
            Single.just(list)
        }
    }

    /**
     * Search for movies, always using remote data source
     */
    override fun searchMovies(query: String): Single<List<Movie>> {
        if(query.isEmpty()) Single.just(emptyList<Movie>())

        return remoteDataSource.searchMovies(query)
    }

    override fun getMovieDetails(id: Int, forceUpdate: Boolean): Single<Movie> {
        return if (forceUpdate) {
            remoteDataSource.getMovieDetails(id)
        } else {
            cacheDataSource.getMovie(id)
        }
    }
}