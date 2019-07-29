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

class MovieListViewModel(
    private val useCase: GetMoviesUseCase, private val uiScheduler: Scheduler
) : BaseViewModel() {

    /**
     * State for the sorting type. It starts with NONE. When the user taps on sort button, it
     * switches between the values from the enum SortingType [MovieListViewModel.toggleSortingMethod]
     */
    private var sortingType = SortingType.NONE

    /**
     * State of the data used in the screen
     */
    val state = MutableLiveData<ViewState<List<Movie>>>().apply {
        value = ViewState.Loading
    }

    /**
     * Fetch a list of movies from the repository and update the view state
     */
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

    /**
     * Event triggered when user taps on "Try again" button.
     */
    fun onTryAgainRequired() {
        getMovies(forceUpdate = true)
    }

    /**
     * Triggered when a user uses the search bar at the top of the view.
     * If the query is empty, it executes the default api call
     */
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

    /**
     * Toggle the sorting method:
     *
     * From SortingType.NONE to SortingType.ASCENDING
     *
     * From SortingType.ASCENDING to SortingType.DESCENDING
     *
     * From SortingType.DESCENDING to SortingType.ASCENDING
     */
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

/**
 * Sort the list of movies based on sorting type. See [MovieListViewModel.sortingType]
 */
private fun List<Movie>.sort(sortingType: SortingType): List<Movie> {
    return when(sortingType) {
        SortingType.NONE -> this
        SortingType.ASCENDING -> this.sortedBy { it.title }
        SortingType.DESCENDING -> this.sortedByDescending { it.title  }
    }
}