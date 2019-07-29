package com.hellmann.bluecoding.data.remote.source.authentication

import com.hellmann.bluecoding.data.remote.api.AuthenticationServerApi
import com.hellmann.bluecoding.data.remote.mapper.AccountPayLoadMapper
import com.hellmann.bluecoding.data.remote.mapper.AuthenticationPayloadMapper
import com.hellmann.bluecoding.domain.entity.Account
import com.hellmann.bluecoding.domain.entity.Authentication
import io.reactivex.Single

class AuthenticationRemoteDataSourceImpl(
    private val api: AuthenticationServerApi
) : AuthenticationRemoteDataSource {

    override fun createGuestUserSession(): Single<Authentication> {
        return api.createNewGuestUserSession().map { AuthenticationPayloadMapper.map(it) }
    }

    override fun getAccount(sessionId: String): Single<Account> {
        return api.getAccount(sessionId).map { AccountPayLoadMapper.map(it) }
    }
}