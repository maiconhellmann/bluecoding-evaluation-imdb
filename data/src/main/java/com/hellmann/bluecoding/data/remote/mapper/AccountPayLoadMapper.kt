package com.hellmann.bluecoding.data.remote.mapper

import com.hellmann.bluecoding.data.remote.model.AccountPayload
import com.hellmann.bluecoding.domain.entity.Account

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */
object AccountPayLoadMapper {
    fun map(payload: AccountPayload): Account {
        return Account(payload.id, payload.name, payload.username)
    }
}