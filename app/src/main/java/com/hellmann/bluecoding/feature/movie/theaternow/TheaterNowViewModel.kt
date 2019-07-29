package com.hellmann.bluecoding.feature.movie.theaternow

import androidx.lifecycle.MutableLiveData
import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.domain.usecase.GetMoviesUseCase
import com.hellmann.bluecoding.feature.viewmodel.BaseViewModel
import com.hellmann.bluecoding.feature.viewmodel.StateMachineSingle
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */class TheaterNowViewModel(
    private val useCase: GetMoviesUseCase, private val uiScheduler: Scheduler
) : BaseViewModel() {
    val state = MutableLiveData<ViewState<List<Movie>>>().apply {
        value = ViewState.Loading
    }

    fun getMoviesInTheater() {
        disposables += useCase.getMoviesInTheater()
            .doOnSubscribe { state.postValue(ViewState.Loading) }.compose(StateMachineSingle())
            .observeOn(uiScheduler).subscribeBy(onSuccess = {
                state.postValue(it)
            })
    }

    fun onTryAgainRequired() {
        getMoviesInTheater()
    }
}