package com.hellmann.bluecoding.domain.usecase

import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

class GetMoviesUseCase(
    private val repository: MovieRepository,
    private val scheduler: Scheduler
) {

    fun execute(forceUpdate: Boolean): Single<List<Movie>> {
        val currentYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())
        return repository.getMovies(forceUpdate, currentYear).subscribeOn(scheduler)
    }
}