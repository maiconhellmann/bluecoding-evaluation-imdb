package com.hellmann.bluecoding.feature.main

import androidx.lifecycle.MutableLiveData
import com.hellmann.bluecoding.domain.entity.Authentication
import com.hellmann.bluecoding.domain.usecase.AuthenticationUseCase
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
 */class MainViewModel(
    private val useCase: AuthenticationUseCase, private val uiScheduler: Scheduler
): BaseViewModel() {
    val state = MutableLiveData<ViewState<Authentication>>().apply {
        value = ViewState.Loading
    }

    fun getAuthentication() {
        disposables += useCase.getAuthentication()
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = {
                    state.postValue(it)
                }
            )
    }
}