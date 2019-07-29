package com.hellmann.bluecoding.data.remote.api

import com.hellmann.bluecoding.data.BuildConfig
import com.hellmann.bluecoding.domain.entity.Authentication
import io.reactivex.Single
import retrofit2.http.GET

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */interface AuthenticationServerApi {
    @GET("${BuildConfig.API_VERSION}/authentication/guest_session/new?api_key=${BuildConfig.API_KEY}")
    fun createNewGuestUserSession(): Single<Authentication>
}