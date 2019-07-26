package com.hellmann.bluecoding.di

import com.hellmann.bluecoding.feature.movie.list.MovieViewModel
import com.hellmann.bluecoding.feature.movie.list.MoviesAdapter
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 10/06/2019
 * 
 * (c) 2019 
 */

val presentationModuleTest = module {
    factory { MoviesAdapter() }

    factory {
        MovieViewModel(
            useCase = get(),
            uiScheduler = Schedulers.trampoline())
    }
}