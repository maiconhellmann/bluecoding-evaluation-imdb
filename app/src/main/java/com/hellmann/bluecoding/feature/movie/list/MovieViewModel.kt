package com.hellmann.bluecoding.feature.movie.list

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

    private var sortingType = SortingType.NONE

    val state = MutableLiveData<ViewState<List<Movie>>>().apply {
        value = ViewState.Loading
    }

    fun getMovies(forceUpdate: Boolean = false) {
        disposables += useCase.getMovies(forceUpdate = forceUpdate)
            .doOnSubscribe { state.postValue(ViewState.Loading) }
            .compose(StateMachineSingle())
            .observeOn(uiScheduler).subscribeBy(
                onSuccess = {
                    //sort if necessary
                    if(it is ViewState.Success) it.data.sort(sortingType)

                    state.postValue(it)
                }
            )
    }

    fun onTryAgainRequired() {
        getMovies(forceUpdate = true)
    }

    fun searchMovies(query: String?) {
        if (query.isNullOrEmpty()) return getMovies(false)

        disposables += useCase.searchMovies(query)
            .doOnSubscribe { state.postValue(ViewState.Loading) }
            .compose(StateMachineSingle())
            .observeOn(uiScheduler).subscribeBy(
                onSuccess = {
                    //sort if necessary
                    if(it is ViewState.Success) it.data.sort(sortingType)

                    state.postValue(it)
                }
            )
    }

    fun toggleSortingMethod() {
        sortingType = when(sortingType) {
            SortingType.NONE -> SortingType.ASCENDING
            SortingType.ASCENDING -> SortingType.DESCENDING
            SortingType.DESCENDING -> SortingType.ASCENDING
        }

        val currentState = state.value
        if (currentState is ViewState.Success) {
            state.postValue(ViewState.Success(currentState.data.sort(sortingType)))
        }
    }
}

private enum class SortingType {
    NONE, ASCENDING, DESCENDING
}

private fun List<Movie>.sort(sortingType: SortingType): List<Movie> {
    return when(sortingType) {
        SortingType.NONE -> this
        SortingType.ASCENDING -> this.sortedBy { it.title }
        SortingType.DESCENDING -> this.sortedByDescending { it.title  }
    }
}