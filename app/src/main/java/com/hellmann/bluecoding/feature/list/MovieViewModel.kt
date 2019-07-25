package com.hellmann.bluecoding.feature.list

import androidx.lifecycle.MutableLiveData
import com.hellmann.bluecoding.domain.entity.Movie
import com.hellmann.bluecoding.domain.usecase.GetMoviesUseCase
import com.hellmann.bluecoding.feature.viewmodel.BaseViewModel
import com.hellmann.bluecoding.feature.viewmodel.StateMachineSingle
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

class MovieViewModel(
    private val useCase: GetMoviesUseCase, private val uiScheduler: Scheduler
) : BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Movie>>>().apply {
        value = ViewState.Loading
    }

    fun getMovies(forceUpdate: Boolean = false) {
        disposables += useCase.execute(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler).subscribeBy(
                onSuccess = {
                    state.postValue(it)
                }
            )
    }

    fun onTryAgainRequired() {
        getMovies(forceUpdate = true)
    }
}