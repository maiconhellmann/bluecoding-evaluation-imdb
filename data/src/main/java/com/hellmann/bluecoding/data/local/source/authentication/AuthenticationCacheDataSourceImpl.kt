package com.hellmann.bluecoding.data.local.source.authentication

import com.hellmann.bluecoding.data.local.database.authentication.AuthenticationDao
import com.hellmann.bluecoding.data.local.mapper.AuthenticationCacheMapper
import com.hellmann.bluecoding.domain.entity.Authentication
import io.reactivex.Single

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
class AuthenticationCacheDataSourceImpl(
    private val dao: AuthenticationDao
) : AuthenticationCacheDataSource {
    override fun getGuestUserSession(): Single<Authentication> {
        return dao.getGuestUserSession().map { AuthenticationCacheMapper.map(it) }
    }

    override fun updateGuestUserSession(authentication: Authentication): Authentication {
        return AuthenticationCacheMapper.map(dao.updateGuestUserSession(AuthenticationCacheMapper.map(authentication)))
    }
}