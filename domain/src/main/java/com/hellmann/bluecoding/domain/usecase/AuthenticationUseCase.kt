package com.hellmann.bluecoding.domain.usecase

import com.hellmann.bluecoding.domain.entity.Account
import com.hellmann.bluecoding.domain.entity.Authentication
import com.hellmann.bluecoding.domain.repository.AuthenticationRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */class AuthenticationUseCase(
    private val repository: AuthenticationRepository,
    private val scheduler: Scheduler
) {
    fun getAuthentication(): Single<Authentication> {
        return repository.getGuestSession().subscribeOn(scheduler).delay(2, TimeUnit.SECONDS)
    }
    fun getAccount(): Single<Account> {
        return repository.getAccount().subscribeOn(scheduler)
    }
}