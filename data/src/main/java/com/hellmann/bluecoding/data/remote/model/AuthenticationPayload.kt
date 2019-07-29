package com.hellmann.bluecoding.data.remote.model

import com.google.gson.annotations.SerializedName

/*
 * This file is part of BlueCodingEvaluationIMDB.
 * 
 * Created by maiconhellmann on 29/07/2019
 * 
 * (c) 2019 
 */class AuthenticationPayload(
    val success: Boolean? = null,
    @SerializedName("guest_session_id")
    val guestSessionId: String? = null,
    @SerializedName("expires_at")
    val expirestAt: String? = null
)