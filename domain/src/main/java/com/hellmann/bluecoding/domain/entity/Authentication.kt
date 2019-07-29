package com.hellmann.bluecoding.domain.entity

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */class Authentication(
    val success: Boolean? = null,
    val guestSessionId: String? = null,
    val expirestAt: String? = null
)