package com.hellmann.bluecoding.di

import com.hellmann.bluecoding.feature.movie.detail.MovieDetailViewModel
import com.hellmann.bluecoding.feature.movie.list.MovieListViewModel
import com.hellmann.bluecoding.feature.movie.list.MoviesAdapter
import com.hellmann.bluecoding.feature.movie.theaternow.TheaterNowAdapter
import com.hellmann.bluecoding.feature.movie.theaternow.TheaterNowViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    //RecyclerView adapters
    factory { MoviesAdapter() }
    factory { TheaterNowAdapter() }

    //Movie List ViewModel
    viewModel {
        MovieListViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread())
    }
    //Movie Detail ViewModel
    viewModel {
        MovieDetailViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread())
    }
    //Movies in theater ViewModel
    viewModel {
        TheaterNowViewModel(useCase = get(), uiScheduler = AndroidSchedulers.mainThread())
    }
}