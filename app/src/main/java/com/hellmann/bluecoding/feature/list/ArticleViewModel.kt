package com.hellmann.bluecoding.feature.list

import androidx.lifecycle.MutableLiveData
import com.hellmann.bluecoding.feature.viewmodel.BaseViewModel
import com.hellmann.bluecoding.feature.viewmodel.StateMachineSingle
import com.hellmann.bluecoding.feature.viewmodel.ViewState
import com.hellmann.bluecoding.domain.entity.Article
import com.hellmann.bluecoding.domain.usecase.GetArticlesUseCase
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

/*
 * This file is part of hellmann-architeture.
 * 
 * Created by maiconhellmann on 26/05/2019
 * 
 * (c) 2019 
 */
class ArticleViewModel(
    private val useCase: GetArticlesUseCase, private val uiScheduler: Scheduler
) : BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Article>>>().apply {
        value = ViewState.Loading
    }

    fun getJobs(forceUpdate: Boolean = false) {
        disposables += useCase.execute(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler).subscribeBy(
                onSuccess = {
                    state.postValue(it)
                }
            )
    }

    fun onTryAgainRequired() {
        getJobs(forceUpdate = true)
    }
}