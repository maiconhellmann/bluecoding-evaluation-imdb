package com.hellmann.bluecoding.data.local.mapper

import com.hellmann.bluecoding.data.local.model.AuthenticationCache
import com.hellmann.bluecoding.domain.entity.Authentication

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
object AuthenticationCacheMapper {
    fun map(cache: AuthenticationCache): Authentication {
        return Authentication(
            success = cache.success,
            expirestAt = cache.expirestAt,
            guestSessionId = cache.guestSessionId)
    }
}