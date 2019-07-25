package com.hellmann.bluecoding.domain.usecase

import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.domain.repository.MovieRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetMoviesUseCase(
    private val repository: MovieRepository,
    private val scheduler: Scheduler
) {

    fun execute(forceUpdate: Boolean): Single<List<Movie>> {
        return repository.getMovies(forceUpdate).subscribeOn(scheduler)
    }
}