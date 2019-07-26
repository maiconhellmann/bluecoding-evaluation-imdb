package com.hellmann.bluecoding.di

import com.hellmann.bluecoding.feature.movie.list.MovieViewModel
import com.hellmann.bluecoding.feature.movie.list.MoviesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { MoviesAdapter() }

    viewModel {
        MovieViewModel(
            useCase = get(), uiScheduler = AndroidSchedulers.mainThread())
    }
}