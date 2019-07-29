package com.hellmann.bluecoding.domain.usecase

import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

class GetMoviesUseCase(
    private val repository: MovieRepository, private val scheduler: Scheduler
) {

    fun getMovies(forceUpdate: Boolean): Single<List<Movie>> {
        val currentYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())
        return repository.getMovies(forceUpdate, currentYear).subscribeOn(scheduler)
    }

    fun searchMovies(query: String) = repository.searchMovies(query).subscribeOn(scheduler)

    fun getMovieDetails(id: Int, forceUpdate: Boolean = false) =
        repository.getMovieDetails(id, forceUpdate).subscribeOn(scheduler)

    fun getMoviesInTheater() = repository.getMoviesInTheater().subscribeOn(scheduler)
}