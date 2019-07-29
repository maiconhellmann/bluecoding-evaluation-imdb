package com.hellmann.bluecoding.feature.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {
    /**
     * Reference to disposables used in the viewModels. When the ViewModel is cleared, it disposes everything.
     */
    val disposables = CompositeDisposable()

    override fun onCleared() {
        //dispose everything
        disposables.clear()

        super.onCleared()
    }
}