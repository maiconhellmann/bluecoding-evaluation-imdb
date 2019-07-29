package com.hellmann.bluecoding.data

import com.hellmann.bluecoding.data.local.source.authentication.AuthenticationCacheDataSource
import com.hellmann.bluecoding.data.remote.source.authentication.AuthenticationRemoteDataSource
import com.hellmann.bluecoding.domain.entity.Authentication
import com.hellmann.bluecoding.domain.repository.AuthenticationRepository
import io.reactivex.Single

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
class AuthenticationRepositoryImpl(
    private val cacheDataSource: AuthenticationCacheDataSource,
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {
    override fun getGuestSession(): Single<Authentication> {
        //Try to get from cache
        return cacheDataSource.getGuestUserSession()
            .onErrorResumeNext {
                //try to get from a remote service
                remoteDataSource.createGuestUserSession().map {
                    //save locally
                    cacheDataSource.updateGuestUserSession(it)
                }
            }
    }
}