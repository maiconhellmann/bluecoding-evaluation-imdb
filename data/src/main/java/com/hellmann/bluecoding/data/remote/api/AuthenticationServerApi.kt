package com.hellmann.bluecoding.data.remote.api

import com.hellmann.bluecoding.data.BuildConfig
import com.hellmann.bluecoding.data.remote.model.AccountPayload
import com.hellmann.bluecoding.data.remote.model.AuthenticationPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */interface AuthenticationServerApi {
    @GET("${BuildConfig.API_VERSION}/authentication/guest_session/new?api_key=${BuildConfig.API_KEY}")
    fun createNewGuestUserSession(): Single<AuthenticationPayload>

    @GET("${BuildConfig.API_VERSION}/account?api_key=${BuildConfig.API_KEY}")
    fun getAccount(@Query("session_id") sessionId: String): Single<AccountPayload>
}