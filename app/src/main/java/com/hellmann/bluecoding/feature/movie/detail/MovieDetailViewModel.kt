package com.hellmann.bluecoding.feature.movie.detail

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
 * Created by maiconhellmann on 26/07/2019
 * 
 * (c) 2019 
 */class MovieDetailViewModel(
    private val useCase: GetMoviesUseCase, private val uiScheduler: Scheduler
) : BaseViewModel() {

    /**
     * View state to be observed by the view
     */
    val state = MutableLiveData<ViewState<Movie>>().apply {
        value = ViewState.Loading
    }

    /**
     * Fetch user detail from repository
     */
    fun getMovieDetail(movieId: Int, forceUpdate: Boolean = true) {
        disposables += useCase.getMovieDetails(movieId, forceUpdate)
            .doOnSubscribe { state.postValue(ViewState.Loading) }.compose(StateMachineSingle())
            .subscribeBy(onSuccess = {
                state.postValue(it)
            })
    }

    /**
     * Event triggered by the button "Try again"
     */
    fun onTryAgainRequired(movieId: Int) {
        getMovieDetail(movieId, forceUpdate = true)
    }
}