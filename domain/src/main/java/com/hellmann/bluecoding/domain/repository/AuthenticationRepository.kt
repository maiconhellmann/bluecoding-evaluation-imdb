package com.hellmann.bluecoding.domain.repository

import com.hellmann.bluecoding.domain.entity.Authentication
import io.reactivex.Single

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */interface AuthenticationRepository {
    fun getGuestSession(): Single<Authentication>
}