package com.hellmann.bluecoding.data.remote.source.authentication

import com.hellmann.bluecoding.domain.entity.Account
import com.hellmann.bluecoding.domain.entity.Authentication
import io.reactivex.Single

interface AuthenticationRemoteDataSource {
    fun createGuestUserSession(): Single<Authentication>
    fun getAccount(sessionId: String): Single<Account>
}