package com.hellmann.bluecoding.data.remote.mapper

import com.hellmann.bluecoding.data.remote.model.AuthenticationPayload
import com.hellmann.bluecoding.domain.entity.Authentication

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */object AuthenticationPayloadMapper {
    fun map(payload: AuthenticationPayload): Authentication {
        return Authentication(
            success = payload.success,
            guestSessionId = payload.guestSessionId,
            expirestAt = payload.expirestAt
        )
    }

}