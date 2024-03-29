package com.hellmann.bluecoding.feature.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hellmann.bluecoding.di.presentationModuleTest
import com.hellmann.bluecoding.domain.usecase.GetMoviesUseCase
import com.hellmann.bluecoding.feature.movie.list.MovieListViewModel
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.Mockito.mock

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 10/06/2019
 * 
 * (c) 2019 
 */
class MovieViewModelTest : AutoCloseKoinTest() {

    val viewModel: MovieListViewModel by inject()
    val useCase: GetMoviesUseCase by inject()

    //A JUnit Test Rule that swaps the background executor used by the Architecture Components with a different one which executes each task synchronously.
    //https://developer.android.com/reference/android/arch/core/executor/testing/InstantTaskExecutorRule
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        val mockUseCase = mock(GetMoviesUseCase::class.java)
        val module = module { factory { mockUseCase } }

        //Needs to be mocked before injection(maybe try using mock by koin
        Mockito.`when`(mockUseCase.getMovies(true)).then {
            //            Single.just(listOf(Movie("Title")))
            //TODO mockit
        }

        startKoin {
            modules(presentationModuleTest + module)
        }
    }

    @Test
    fun viewModelTest() {

        assert(viewModel.state.value == ViewState.Loading)

        viewModel.getMovies(true)

        assert(viewModel.state.value is ViewState.Success)

        with(viewModel.state.value as ViewState.Success) {
            assert(data.isNotEmpty())
            assert(data.first().title == "Title")
        }
    }
}